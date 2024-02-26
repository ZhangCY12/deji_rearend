package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.CncClasses;

public interface CncClassesService extends IService<CncClasses> {
    void insertOperationDay();
    void insertOperationNight();
}
