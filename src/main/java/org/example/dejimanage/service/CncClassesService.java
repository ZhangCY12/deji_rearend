package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.CncClasses;

public interface CncClassesService extends IService<CncClasses> {
    void insertOperationDay(); //向t_cnc_classes_operation表插入当天白班的稼动率和运行时间信息
    void insertOperationNight(); //向t_cnc_classes_operation表插入上一天夜班的稼动率和运行时间信息
    void exportExcelOfRate(); //导出当天早晚班稼动率excel表到目标目录
}
