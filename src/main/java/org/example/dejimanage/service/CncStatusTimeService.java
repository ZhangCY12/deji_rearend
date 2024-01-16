package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.CncStatusTime;


import java.util.List;

public interface CncStatusTimeService extends IService<CncStatusTime> {
    public List<CncStatusTime> GetAllCncStatusTime();
}
