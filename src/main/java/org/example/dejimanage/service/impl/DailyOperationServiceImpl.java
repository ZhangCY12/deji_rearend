package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dejimanage.entity.CncStatusTime;
import org.example.dejimanage.entity.DailyOperation;
import org.example.dejimanage.mapper.CncStatusTimeMapper;
import org.example.dejimanage.mapper.DailyOperationMapper;
import org.example.dejimanage.service.DailyOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class DailyOperationServiceImpl extends ServiceImpl<DailyOperationMapper, DailyOperation> implements DailyOperationService {

    @Autowired
    private DailyOperationMapper dailyOperationMapper;
    @Autowired
    private CncStatusTimeMapper cncStatusTimeMapper;

    //日志记录
    private static final Logger logger = LoggerFactory.getLogger(DailyOperationServiceImpl.class);//日志记录对象

    /***
     * 插入历史机台运行情况表
     */
    @Override
    @Transactional
    public void insertMachineDailyOperation() {
        List<CncStatusTime> cncStatusTimeList = cncStatusTimeMapper.selectList(null);
        // 对查询到的数据进行处理
        for(CncStatusTime data : cncStatusTimeList){
            DailyOperation dailyOperation = new DailyOperation();
            LocalDate localDate = LocalDate.now();
            Date sqlDate = Date.valueOf(localDate);
            dailyOperation.setDate(sqlDate);
            dailyOperation.setMachineId(data.getCncNum());
            dailyOperation.setRunTime(data.getRunTime());
            dailyOperation.setStandbyTime(data.getIdleTime());
            dailyOperation.setErrorTime(data.getErrorTime());
            dailyOperation.setOperationRate(data.getUtilizationRate());
            dailyOperationMapper.insert(dailyOperation);
        }
        logger.info("定时任务_插入当天机台总稼动率、运行时间");
    }

}
