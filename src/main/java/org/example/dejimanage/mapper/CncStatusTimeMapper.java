package org.example.dejimanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.dejimanage.entity.CncStatusTime;

import java.util.List;
import java.util.Map;

@Mapper
public interface CncStatusTimeMapper extends BaseMapper<CncStatusTime> {
    /***
     * 根据Id查询单台机稼动率、运行时间等信息
     */
    @Select("SELECT cnc_num,utilization_rate " +
            "FROM t_cnc_status_time " +
            "WHERE cnc_num = #{id}")
    Map<String,Object> selectCncRateByid(int id);


    /***
     * 根据id查询单机的运行时间情况
     */
    @Select("SELECT run_time,idle_time,error_time " +
            "FROM t_cnc_status_time " +
            "WHERE cnc_num = #{id}")
    CncStatusTime selectCncRuntimeByid(int id);


    @Select("SELECT cnc_num,run_time,idle_time,error_time,utilization_rate " +
            "FROM t_cnc_status_time")
    List<Map<String,Object>> selectAllCnc();
}
