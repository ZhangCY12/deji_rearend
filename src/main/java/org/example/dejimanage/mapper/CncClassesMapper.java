package org.example.dejimanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.dejimanage.entity.CncClasses;

@Mapper
public interface CncClassesMapper extends BaseMapper<CncClasses> {
    @Select("INSERT INTO t_cnc_classes_operation (machine_id, date, classes, operation_rate, run_time, standby_time, error_time) " +
            "VALUES (#{machineId},#{date},#{classes},#{operationRate},#{runTime},#{standbyTime},#{errorTime}) ")
    void insertCncClassesOne(CncClasses cncClasses);
}
