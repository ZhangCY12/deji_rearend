package org.example.dejimanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.dejimanage.entity.MaintenanceInfo;

import java.util.Map;

@Mapper
public interface MaintenanceInfoMapper extends BaseMapper<MaintenanceInfo> {
    @Select("SELECT machine_id,last_time,next_time,last_repairtime " +
            "FROM t_cnc_maintenance " +
            "WHERE machine_id = #{id}")
    Map<String,Object> selectCncMaintenanceByid(int id);
}
