package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.CNCs;

import java.util.List;
import java.util.Map;

public interface CNCsService extends IService<CNCs> {
    public List<String> GetAllCncStatus();
    public Map<String, Integer> GetALLNumberOfCncStatus();
    public List<Map<String, Object>> getDeviceAndCount();
}
