package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dejimanage.entity.DeviceProfile;
import org.example.dejimanage.mapper.DeviceProfileMapper;
import org.example.dejimanage.service.DeviceProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DeviceProfileServiceImpl extends ServiceImpl<DeviceProfileMapper, DeviceProfile> implements DeviceProfileService {

    @Autowired
    private DeviceProfileMapper deviceProfileMapper;


    /***
     * 根据id查询CNC的设备档案信息
     * @param id 设备Id
     */
    @Override
    public Map<String, Object> getCncDeviceProfileByid(int id) {
        return deviceProfileMapper.selectCNCdeviceProfileByid(id);
    }
}
