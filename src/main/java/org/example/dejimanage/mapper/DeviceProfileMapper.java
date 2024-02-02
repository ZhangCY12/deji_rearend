package org.example.dejimanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.dejimanage.entity.DeviceProfile;

import java.util.Map;

@Mapper
public interface DeviceProfileMapper extends BaseMapper<DeviceProfile> {
    /***
     * 根据id查询CNC的设备档案信息
     */
    @Select("SELECT machine_id as id, asset_number,use_departments,name" +
            ",user,storage_location,DATE_FORMAT(grade_date, '%Y-%m-%d') as date " +
            "FROM t_cnc_deviceprofile WHERE machine_id = #{id}")
    Map<String,Object> selectCNCdeviceProfileByid(@Param("id") int id);
}
