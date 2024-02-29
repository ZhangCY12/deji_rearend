package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.CncAlarmAll;
import java.util.List;

public interface CncAlarmAllService extends IService<CncAlarmAll> {
    void recordDailyMachineAlarms(); //插入数据表中当天所有机台的报警次数
    List<CncAlarmAll> getAlarmHisBuid(int id); //根据Id查询历史报警次数信息
}
