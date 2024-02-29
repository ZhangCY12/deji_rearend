package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.CncStatusTime;


import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface CncStatusTimeService extends IService<CncStatusTime> {
    List<CncStatusTime> getAllCncStatusTime(); //查询所有cnc机台的稼动率、运行时间、待机时间、异常时间
    Map<String,Object> getCncStatusByid(int id); //根据ID查询单机稼动率
    Map<String,Object> getCncRuntimeByid(int id); //根据Id查询单机实时运行时间情况
    List<Map<String,Object>> getCncHistoryRateByid(int id) throws ParseException; //根据Id查询历史稼动率信息(由每天白班和夜班数据统计出)
}
