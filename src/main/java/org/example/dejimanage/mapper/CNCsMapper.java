package org.example.dejimanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
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

    // 插入当日产能和总产能
    @Insert("INSERT INTO t_cnc_production (machine_id, date, daily_production, total_production) " +
            "SELECT r.cnc_num, CURDATE(), " +
            "COALESCE(CAST(NULLIF(r.artifacts_all, '') AS DECIMAL) - " +
            "COALESCE((SELECT CAST(NULLIF(total_production, '') AS DECIMAL) FROM t_cnc_production WHERE machine_id = r.cnc_num AND date = SUBDATE(CURDATE(), 1)), 0), 0), " +
            "CAST(NULLIF(r.artifacts_all, '') AS DECIMAL) " +
            "FROM t_cnc_run r")
    void insertAllArtifacts();

    /***
     * 根据id查询所有信息
     */
    @Select("SELECT * FROM t_cnc_run WHERE cnc_num = #{id}")
    Map<String,Object> selectAllByid(int id);

    /***
     * 查询所有机台的id
     */
    @Select("SELECT cnc_no FROM t_cnc_run")
    List<String> selectAllMachineIds();
}
