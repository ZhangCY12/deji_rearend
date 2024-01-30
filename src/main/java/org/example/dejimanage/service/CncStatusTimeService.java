package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.CncStatusTime;


import java.util.List;
import java.util.Map;

public interface CncStatusTimeService extends IService<CncStatusTime> {
    List<CncStatusTime> getAllCncStatusTime();
    Map<String,Object> getCncStatusByid(int id);

    Map<String,Object> getCncRuntimeByid(int id);

    List<Map<String,Object>> getCncHistoryRateByid(int id);
}
