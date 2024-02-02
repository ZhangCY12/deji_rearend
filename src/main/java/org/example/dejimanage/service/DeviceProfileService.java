package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.DeviceProfile;

import java.util.Map;

public interface DeviceProfileService extends IService<DeviceProfile> {
    Map<String,Object> getCncDeviceProfileByid(int id);
}
