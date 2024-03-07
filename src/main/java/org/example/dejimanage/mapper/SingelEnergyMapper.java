package org.example.dejimanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.dejimanage.entity.SingelEnergy;

import java.util.List;
import java.util.Map;

@Mapper
public interface SingelEnergyMapper extends BaseMapper<SingelEnergy> {
    @Select("SELECT * FROM t_cnc_classes_energy ")
    List<Map<String, Object>> selectALL();
}
