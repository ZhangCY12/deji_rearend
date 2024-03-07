package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.SingelEnergy;

import java.util.List;
import java.util.Map;
public interface SingelEnergyService extends IService<SingelEnergy> {
    List<Map<String,Object>> selectAllSingleEnergy();
}
