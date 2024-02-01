package org.example.dejimanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.dejimanage.entity.CncAlarm;

import java.util.List;
import java.util.Map;

@Mapper
public interface CncAlarmMapper extends BaseMapper<CncAlarm> {

    /***
     * 根据Id查询当天的前30条报警
     */
    @Select("SELECT device,TIME(CONVERT(time USING utf8mb4)) AS time,alarm " +
            "FROM t_cnc_alarm " +
            "WHERE DATE(CONVERT(time USING utf8mb4)) = CURDATE() " +
            "AND device = #{cncno} " +
            "ORDER BY CONVERT(time USING utf8mb4) " +
            "DESC LIMIT 30")
    List<CncAlarm> selectTodayAlarms(String cncno);


    /***
     * 查询每台机当天的报警次数
     */
    @Select("SELECT device AS machine_number, COUNT(*) AS alarm_count " +
            "FROM t_cnc_alarm " +
            "WHERE DATE(CONVERT(time USING utf8mb4)) = CURDATE() " +
            "GROUP BY device")
    List<Map<String, Object>> selectDailyMachineAlarms();
}
