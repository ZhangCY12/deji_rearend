package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.EnergyTotal;

import java.util.List;

public interface EnergyTotalService extends IService<EnergyTotal> {
    public List<EnergyTotal> GetAllEnergy();
    public List<EnergyTotal> GetNowEnergy();
}
