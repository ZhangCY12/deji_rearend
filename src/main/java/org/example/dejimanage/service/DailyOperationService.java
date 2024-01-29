package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.DailyOperation;

public interface DailyOperationService extends IService<DailyOperation> {
    void insertMachineDailyOperation();
}
