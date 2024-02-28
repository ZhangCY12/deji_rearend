package org.example.dejimanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.dejimanage.entity.EnergyTotal;

import java.util.List;
import java.util.Map;

@Mapper
public interface EnergyTotalMapper extends BaseMapper<EnergyTotal> {
    /***
     * 查询过去11天的温湿度和用电量
     */
    @Select("SELECT date, " +
            "CAST(active_Energy1 AS DECIMAL(10,2)) + CAST(active_Energy2 AS DECIMAL(10,2)) + CAST(active_Energy3 AS DECIMAL(10,2)) AS activeEnergyTotal," +
            "CAST(humidity1 AS DECIMAL(10,2)) + CAST(humidity2 AS DECIMAL(10,2)) + CAST(humidity3 AS DECIMAL(10,2)) AS humidity," +
            "CAST(temperature1 AS DECIMAL(10,2)) + CAST(temperature2 AS DECIMAL(10,2)) + CAST(temperature3 AS DECIMAL(10,2)) AS temperature " +
            "FROM t_energy_total " +
            "WHERE STR_TO_DATE(date, '%Y-%m-%d') >= CURDATE() - INTERVAL 11 DAY  " +
            "ORDER BY date DESC " +
            "LIMIT 11")
    List<Map<String,Object>> selectEnergyUsageLastTenDays();


    /***
     *  查询当天的温湿度和用电量
     */
    @Select("SELECT date, " +
            "CAST(active_Energy1 AS DECIMAL(10,2)) + CAST(active_Energy2 AS DECIMAL(10,2)) + CAST(active_Energy3 AS DECIMAL(10,2)) AS activeEnergyTotal," +
            "CAST(humidity1 AS DECIMAL(10,2)) + CAST(humidity2 AS DECIMAL(10,2)) + CAST(humidity3 AS DECIMAL(10,2)) AS humidity," +
            "CAST(temperature1 AS DECIMAL(10,2)) + CAST(temperature2 AS DECIMAL(10,2)) + CAST(temperature3 AS DECIMAL(10,2)) AS temperature " +
            "FROM t_energy_total " +
            "WHERE STR_TO_DATE(date, '%Y-%m-%d') >= CURDATE() - INTERVAL 1 DAY " +
            "ORDER BY date DESC " +
            "LIMIT 1")
    List<Map<String,Object>> selectEnergyNowDay();
}
