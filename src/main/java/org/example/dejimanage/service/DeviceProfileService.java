package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.DeviceProfile;

import java.util.Map;

public interface DeviceProfileService extends IService<DeviceProfile> {
    Map<String,Object> getCncDeviceProfileByid(int id); //根据id查询CNC的设备档案信息
}
