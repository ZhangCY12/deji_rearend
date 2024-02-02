package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.EnergyTotal;

import java.util.List;
import java.util.Map;

public interface EnergyTotalService extends IService<EnergyTotal> {
    List<Map<String,Object>> GetAllEnergy();
    List<Map<String,Object>> GetNowEnergy();
}
