package org.example.dejimanage.tools;

import org.example.dejimanage.config.Constants;
import org.example.dejimanage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    private ProjectOrderService projectOrderService;
    @Autowired
    private DailyOperationService dailyOperationService;
    @Autowired
    private CNCsService cnCsService;
    @Autowired
    private CncAlarmAllService cncAlarmAllService;
    @Autowired
    private CncClassesService cncClassesService;

    /***
     * 定时任务：清理2024年以来工单表在redis中的缓存
     */
    @Scheduled(fixedRate = Constants.PROJECT_TIME)
    public void transferData() {
        projectOrderService.clearProjectCache();
    }

    /***
     * 定时任务：记录每天所有机台的稼动率和运行时间情况
     */
    @Scheduled(cron = Constants.CNC_DAILY_OPERATION_TIME)
    public void insertIntotCncDailyOperation() {
        dailyOperationService.insertMachineDailyOperation();
    }

    /***
     * 定时任务：记录每天的报警次数
     */
    @Scheduled(cron = Constants.CNC_DAILY_OPERATION_TIME)
    public void insertCncDailyAlarm() {
        cncAlarmAllService.recordDailyMachineAlarms();
    }

    /***
     * 定时任务：记录当天白班的稼动率和运行时间信息
     */
    @Scheduled(cron = Constants.CNC_DAILY_OPERATION_白)
    public void insertCncOperationDay(){
        cncClassesService.insertOperationDay();
    }

    /***
     * 定时任务：记录前一天天夜班的稼动率和运行时间信息
     */
    @Scheduled(cron = Constants.CNC_DAILY_OPERATION_夜)
    public void insertCncOperationNight(){
        cncClassesService.insertOperationNight();
    }
}
