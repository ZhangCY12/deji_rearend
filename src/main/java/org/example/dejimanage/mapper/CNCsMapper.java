package org.example.dejimanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.dejimanage.entity.CNCs;

import java.util.List;
import java.util.Map;

@Mapper
public interface CNCsMapper extends BaseMapper<CNCs> {
    /***
     * 查询在线的机台状态及其数量
     */
    @Select("SELECT cnc_status, COUNT(*) as count " +
            "FROM t_cnc_run " +
            "WHERE online_status = '在线' " +
            "GROUP BY cnc_status")
    List<Map<String, Object>> countByStatus();


    /***
     * 查询CNC设备名称及其数量
     */
    @Select("SELECT commucation AS name, " +
            "COUNT(*) AS count " +
            "FROM t_cnc_run " +
            "GROUP BY commucation")
    List<Map<String, Object>> selectDeviceAndCount();

    /***
     * 查询主轴速度及切削速度
     */
    @Select("SELECT cnc_num,speed_main,speed_cut " +
            "FROM t_cnc_run " +
            "WHERE cnc_num = #{id}")
    Map<String,Object> selectSpeedOfMainCut(int id);
}
