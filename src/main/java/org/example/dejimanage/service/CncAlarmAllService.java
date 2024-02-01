package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.CncAlarmAll;
import java.util.List;

public interface CncAlarmAllService extends IService<CncAlarmAll> {
    void recordDailyMachineAlarms();
    List<CncAlarmAll> getAlarmHisBuid(int id);
}
