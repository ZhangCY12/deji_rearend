package org.example.dejimanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.dejimanage.entity.CncProduction;

import java.util.List;
import java.util.Map;

@Mapper
public interface CncProductionMapper extends BaseMapper<CncProduction> {
    /***
     * 按日期降序查询id的每日产量信息
     */
    @Select("SELECT date,daily_production " +
            "FROM t_cnc_production " +
            "WHERE machine_id = #{id} " +
            "ORDER BY date DESC " +
            "LIMIT 15")
    List<Map<String,Object>> selectProductionByid(int id);
}
