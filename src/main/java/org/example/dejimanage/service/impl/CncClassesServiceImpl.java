package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.dejimanage.entity.CncClasses;
import org.example.dejimanage.mapper.CncClassesMapper;
import org.example.dejimanage.mapper.CncStatusTimeMapper;
import org.example.dejimanage.service.CncClassesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

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
        logger.info("插入白班稼动率、时间数据");
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
        logger.info("插入夜班稼动率、时间数据");
    }

    /***
     * 导出每天早晚班稼动率excel表到目标目录
     */
    @Override
    public void exportExcelOfRate() {
        List<Map<String, Object>> dataList = getDataList(); // 自定义方法，获取数据列表
        exportToExcel(dataList, "D:/output.xlsx"); // 输出到 D 盘的 output.xlsx 文件
        logger.info("导出夜班稼动率、时间数据EXCEL表 D:/output.xlsx");
    }

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
                }
            }
            // 写入到文件
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                System.out.println("Excel导出成功：" + filePath);
            } catch (IOException e) {
                System.out.println("写入Excel文件时发生错误：" + e.getMessage());
            }

        } catch (IOException e) {
            System.out.println("创建Excel文件时发生错误：" + e.getMessage());
        }
    }

    private static List<Map<String, Object>> getDataList() {
        // 这里是一个示例数据，实际使用时根据你的数据结构和来源进行替换
        List<Map<String, Object>> dataList = new ArrayList<>();

        Map<String, Object> data1 = new LinkedHashMap<>();
        data1.put("Name", "Alice");
        data1.put("Age", 28);
        data1.put("City", "New York");
        dataList.add(data1);

        Map<String, Object> data2 = new LinkedHashMap<>();
        data2.put("Name", "Bob");
        data2.put("Age", 35);
        data2.put("City", "Los Angeles");
        dataList.add(data2);

        return dataList;
    }
}
