package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.EnergyTotal;

import java.util.List;
import java.util.Map;

public interface EnergyTotalService extends IService<EnergyTotal> {
    List<Map<String,Object>> GetAllEnergy(); //计算过去10天，每天的平均温湿度，和用电量
    List<Map<String,Object>> GetNowEnergy(); //查询当天的用电量、温湿度信息
}
