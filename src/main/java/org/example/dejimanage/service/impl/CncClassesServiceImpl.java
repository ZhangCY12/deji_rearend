package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.dejimanage.entity.CncClasses;
import org.example.dejimanage.mapper.CncClassesMapper;
import org.example.dejimanage.mapper.CncStatusTimeMapper;
import org.example.dejimanage.service.CncClassesService;
import org.example.dejimanage.tools.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CncClassesServiceImpl extends ServiceImpl<CncClassesMapper, CncClasses> implements CncClassesService {

    @Autowired
    private CncClassesMapper cncClassesMapper;
    @Autowired
    private CncStatusTimeMapper cncStatusTimeMapper;

    private static final Logger logger = LoggerFactory.getLogger(CncClassesServiceImpl.class);

    /***
     * 向t_cnc_classes_operation表插入当天白班的稼动率和运行时间信息
     */
    @Override
    @Transactional
    public void insertOperationDay() {
        List<Map<String, Object>> results = cncStatusTimeMapper.selectAllCnc();
        for(int i = 0; i < results.size(); i++) {
            CncClasses cncClasses = new CncClasses();
            Map<String, Object> entry = results.get(i);
            cncClasses.setDate(new Date());
            cncClasses.setClasses("白班");
            cncClasses.setMachineId((Integer) entry.get("cnc_num"));
            cncClasses.setOperationRate((String) entry.get("utilization_rate"));
            cncClasses.setRunTime((String) entry.get("run_time"));
            cncClasses.setStandbyTime((String) entry.get("idle_time"));
            cncClasses.setErrorTime((String) entry.get("error_time"));
            cncClassesMapper.insertCncClassesOne(cncClasses);
        }
        logger.info("定时任务_插入白班稼动率、时间数据");
    }

    /***
     * 向t_cnc_classes_operation表插入上一天夜班的稼动率和运行时间信息
     */
    @Override
    @Transactional
    public void insertOperationNight() {
        List<Map<String, Object>> results = cncStatusTimeMapper.selectAllCnc();
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        // 减去一天得到前一天的日期
        LocalDate previousDate = currentDate.minusDays(1);
        // 将前一天的 LocalDate 转换为 java.sql.Date
        Date previousSqlDate = new Date(previousDate.toEpochDay() * 24 * 60 * 60 * 1000);
        for(int i = 0; i < results.size(); i++) {
            CncClasses cncClasses = new CncClasses();
            Map<String, Object> entry = results.get(i);
            cncClasses.setDate(previousSqlDate);
            cncClasses.setClasses("夜班");
            cncClasses.setMachineId((Integer) entry.get("cnc_num"));
            cncClasses.setOperationRate((String) entry.get("utilization_rate"));
            cncClasses.setRunTime((String) entry.get("run_time"));
            cncClasses.setStandbyTime((String) entry.get("idle_time"));
            cncClasses.setErrorTime((String) entry.get("error_time"));
            cncClassesMapper.insertCncClassesOne(cncClasses);
        }
        logger.info("定时任务_插入夜班稼动率、时间数据");
    }

    /***
     * 导出当天早晚班稼动率excel表到目标目录
     */
    @Override
    public void exportExcelOfRate() {
        List<Map<String, Object>> result = cncClassesMapper.selectRateToday(DateUtils.formatDateOnly(LocalDate.now(),1)); // 自定义方法，获取数据列表
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            Map<String,Object> map = new HashMap<>();
            Map<String, Object> entry = result.get(i);
            map.put("日期",entry.get("时间"));
            map.put("机器号",entry.get("机器号"));
            map.put("总稼动率",entry.get("总稼动率"));
            map.put("白班稼动率",entry.get("白班稼动率"));
            map.put("夜班稼动率",entry.get("夜班稼动率"));
            dataList.add(map);
        }

        exportToExcel(dataList, "D:/" + DateUtils.formatDateOnly(LocalDate.now(),1) + "稼动率.xlsx"); // 导出到 D 盘
        logger.info("定时任务_导出夜班稼动率、时间数据EXCEL表");
    }

    /***
     * 将数据（List<Map<String,Object>>类型）以Excel文件导出到指定目录
     * @param dataList 目标list
     * @param filePath 文件目标目录
     */
    public static void exportToExcel(List<Map<String, Object>> dataList, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Sheet1");

            // 创建表头
            Row headerRow = sheet.createRow(0);
            int colIdx = 0;
            for (String key : dataList.get(0).keySet()) {
                Cell cell = headerRow.createCell(colIdx++);
                cell.setCellValue(key);
            }
            // 写入数据
            int rowIdx = 1;
            for (Map<String, Object> data : dataList) {
                Row row = sheet.createRow(rowIdx++);
                colIdx = 0;
                for (Object value : data.values()) {
                    Cell cell = row.createCell(colIdx++);
                    if (value instanceof String) {
                        cell.setCellValue((String) value);
                    } else if (value instanceof Integer) {
                        cell.setCellValue((Integer) value);
                    } else if (value instanceof Double) {
                        cell.setCellValue((Double) value);
                    } else if (value instanceof Boolean) {
                        cell.setCellValue((Boolean) value);
                    }
                    // 如果有其他数据类型，可以继续扩展

                    // 设置其他非百分比格式的单元格文本居中
                    CellStyle style = workbook.createCellStyle();
                    style.setAlignment(HorizontalAlignment.CENTER);
                    cell.setCellStyle(style);

                }
            }
            // 自动调整列宽
            for (int i = 0; i < dataList.get(0).size(); i++) {
                sheet.autoSizeColumn(i);
            }

            // 写入到文件
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                logger.info("Excel导出成功：" + filePath);
            } catch (IOException e) {
                logger.error("写入Excel文件时发生错误：" + e.getMessage());
            }

        } catch (IOException e) {
            logger.error("创建Excel文件时发生错误：" + e.getMessage());
        }
    }

}
