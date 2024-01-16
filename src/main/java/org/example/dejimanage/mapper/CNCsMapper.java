package org.example.dejimanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.dejimanage.entity.CNCs;

import java.util.List;
import java.util.Map;

@Mapper
public interface CNCsMapper extends BaseMapper<CNCs> {
    @Select("SELECT cnc_status, COUNT(*) as count FROM t_cnc_run WHERE online_status = '在线' GROUP BY cnc_status")
    List<Map<String, Object>> countByStatus();
}
