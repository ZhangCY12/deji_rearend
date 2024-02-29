package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dejimanage.entity.DeviceProfile;
import org.example.dejimanage.mapper.DeviceProfileMapper;
import org.example.dejimanage.service.DeviceProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class DeviceProfileServiceImpl extends ServiceImpl<DeviceProfileMapper, DeviceProfile> implements DeviceProfileService {

    @Autowired
    private DeviceProfileMapper deviceProfileMapper;

    private static final Logger logger = LoggerFactory.getLogger(DeviceProfileServiceImpl.class);

    /***
     * 根据id查询CNC的设备档案信息
     * @param id 设备Id
     */
    @Override
    public Map<String, Object> getCncDeviceProfileByid(int id) {
        logger.info("请求(cnc)_根据id查询CNC的设备档案信息:(id=" + id +")");
        return deviceProfileMapper.selectCNCdeviceProfileByid(id);
    }
}
