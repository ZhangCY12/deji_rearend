package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.CncProduction;

import java.util.List;
import java.util.Map;

public interface CncProductionService extends IService<CncProduction> {
    List<Map<String,Object>> getCncProductionByid(int id);
}
