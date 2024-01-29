package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dejimanage.entity.MaintenanceInfo;
import org.example.dejimanage.mapper.MaintenanceInfoMapper;
import org.example.dejimanage.service.MaintenanceInfoService;
import org.example.dejimanage.tools.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MaintenanceInfoImpl  extends ServiceImpl<MaintenanceInfoMapper, MaintenanceInfo> implements MaintenanceInfoService {

    @Autowired
    private MaintenanceInfoMapper maintenanceInfoMapper;


    /***
     * 根据id查询CNC的维修保养时间信息
     * @param id
     */
    @Override
    public Map<String, Object> getCncMaintenanceByid(int id) {
        Map<String,Object> map = maintenanceInfoMapper.selectCncMaintenanceByid(id);
        Map<String,Object> map1 = new HashMap<>();

        for (Map.Entry<String,Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if(value instanceof Date){
                map1.put(key,DateUtils.formatDateOnly((Date) value));
            }else{
                map1.put(key,value);
            }
        }
        return map1;
    }
}
