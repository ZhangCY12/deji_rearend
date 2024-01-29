package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.MaintenanceInfo;

import java.util.Map;

public interface MaintenanceInfoService extends IService<MaintenanceInfo> {
    Map<String,Object> getCncMaintenanceByid(int id);
}
