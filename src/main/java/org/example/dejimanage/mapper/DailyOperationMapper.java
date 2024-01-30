package org.example.dejimanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.dejimanage.entity.DailyOperation;

import java.util.List;
import java.util.Map;

@Mapper
public interface DailyOperationMapper extends BaseMapper<DailyOperation> {
    /***
     * 根据id查询过去10天的稼动率信息
     */
    @Select("SELECT date,operation_rate " +
            "FROM t_cnc_daily_operation " +
            "WHERE machine_id = #{id} " +
            "ORDER BY date DESC " +
            "LIMIT 10")
    List<Map<String,Object>> selectRuntimeByid(int id);
}
