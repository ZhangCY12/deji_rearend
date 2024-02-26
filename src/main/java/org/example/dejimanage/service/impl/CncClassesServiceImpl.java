package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dejimanage.entity.CncClasses;
import org.example.dejimanage.mapper.CncClassesMapper;
import org.example.dejimanage.mapper.CncStatusTimeMapper;
import org.example.dejimanage.service.CncClassesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        List<Map<String,Object>> results = cncStatusTimeMapper.selectAllCnc();
        LocalDate localDate = LocalDate.now();
        Date sqlDate = java.sql.Date.valueOf(localDate);
        for(int i = 0;i < results.size();i++){
            CncClasses cncClasses = new CncClasses();
            Map<String,Object> entry = results.get(i);
            cncClasses.setDate(sqlDate);
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
    public void insertOperationNight() {
        List<Map<String,Object>> results = cncStatusTimeMapper.selectAllCnc();
        // 获取当前日期
        LocalDate currentDate = LocalDate.now();
        // 减去一天得到前一天的日期
        LocalDate previousDate = currentDate.minusDays(1);
        // 将前一天的 LocalDate 转换为 java.sql.Date
        Date previousSqlDate = new Date(previousDate.toEpochDay() * 24 * 60 * 60 * 1000);
        for(int i = 0;i < results.size();i++){
            CncClasses cncClasses = new CncClasses();
            Map<String,Object> entry = results.get(i);
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
}
