package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.CNCs;

import java.util.List;
import java.util.Map;

public interface CNCsService extends IService<CNCs> {
    List<String> getAllCncStatus(); //查询所有CNC机台的状态
    Map<String, Integer> getALLNumberOfCncStatus(); //查询cnc机台各个状态和数量
    List<Map<String, Object>> getDeviceAndCount(); //查询机台的种类及其数量
    Map<String,Object> getSpeedOfMainCut(int id); //根据Id查询主轴速度和切削速度
    Map<String,Object> getAllInfoByid(int id); //根据id查询所有CNC信息
}
