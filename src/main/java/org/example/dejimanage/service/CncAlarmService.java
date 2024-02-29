package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.CncAlarm;

import java.util.List;

public interface CncAlarmService extends IService<CncAlarm> {
    List<CncAlarm> getCncAllAlarmByid(int id); //根据id查询当天的报警信息
}
