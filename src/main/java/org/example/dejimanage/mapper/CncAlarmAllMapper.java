package org.example.dejimanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.dejimanage.entity.CncAlarmAll;
import java.util.Date;

@Mapper
public interface CncAlarmAllMapper extends BaseMapper<CncAlarmAll> {

    /***
     *  根据id和日期查询当日报警次数
     */
    @Select("SELECT alarm_number FROM t_cnc_alarm_total " +
            "WHERE machine_id = #{id} AND DATE(date) = DATE(#{date})")
    Integer selectAlarmCountByDate(@Param("id") int id, @Param("date") Date date);
}
