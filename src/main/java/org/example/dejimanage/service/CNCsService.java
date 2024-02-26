package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.CNCs;

import java.util.List;
import java.util.Map;

public interface CNCsService extends IService<CNCs> {
    List<String> getAllCncStatus();
    Map<String, Integer> getALLNumberOfCncStatus();
    List<Map<String, Object>> getDeviceAndCount();
    Map<String,Object> getSpeedOfMainCut(int id);
    Map<String,Object> getAllInfoByid(int id);
}
