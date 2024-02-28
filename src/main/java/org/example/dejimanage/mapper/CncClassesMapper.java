package org.example.dejimanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.dejimanage.entity.CncClasses;

import java.util.List;
import java.util.Map;

@Mapper
public interface CncClassesMapper extends BaseMapper<CncClasses> {

    /***
     *  插入历史稼动率运行时间表一条数据
     */
    @Select("INSERT INTO t_cnc_classes_operation (machine_id, date, classes, operation_rate, run_time, standby_time, error_time) " +
            "VALUES (#{machineId},#{date},#{classes},#{operationRate},#{runTime},#{standbyTime},#{errorTime}) ")
    void insertCncClassesOne(CncClasses cncClasses);

    /***
     * 查询过去10天（20条）的白夜班稼动率数据
     */
    @Select("SELECT date,classes,operation_rate " +
            "FROM t_cnc_classes_operation " +
            "WHERE machine_id = #{id} " +
            "ORDER BY date DESC " +
            "LIMIT 20")
    List<Map<String,Object>> selectCncClassesByid(@Param("id") int id);
}
