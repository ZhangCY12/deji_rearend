package org.example.dejimanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.dejimanage.entity.ProjectOrder;

import java.util.List;

@Mapper
public interface ProjectOrderMapper extends BaseMapper<ProjectOrder> {
    @Select("SELECT * " +
            "FROM t_liteweb_project " +
            "WHERE STR_TO_DATE(createtime, '%Y-%m-%d %H:%i:%s') >= '2024-01-01' ")
    List<ProjectOrder> selectOrdersFrom2024();

    // 2024年已完成的工单数量（结束时间不为空）
    @Select("SELECT COUNT(*) FROM t_liteweb_project WHERE actualstarttime != '' AND actualendtime != '' AND STR_TO_DATE(createtime, '%Y-%m-%d %H:%i:%s') >= '2024-01-01'")
    int countCompletedOrders();

    // 2024年加工中的工单数量（开始时间不为空且结束时间为空）
    @Select("SELECT COUNT(*) FROM t_liteweb_project WHERE actualstarttime != '' AND actualendtime = '' AND STR_TO_DATE(createtime, '%Y-%m-%d %H:%i:%s') >= '2024-01-01'")
    int countProcessingOrders();


    // 2024年未开始的工单数量（开始时间为空）
    @Select("SELECT COUNT(*) FROM t_liteweb_project WHERE actualstarttime = '' AND actualendtime = '' AND STR_TO_DATE(createtime, '%Y-%m-%d %H:%i:%s') >= '2024-01-01'")
    int countNotStartedOrders();

    //2024年所有工单总数
    @Select("SELECT COUNT(*) FROM t_liteweb_project WHERE STR_TO_DATE(createtime, '%Y-%m-%d %H:%i:%s') >= '2024-01-01'")
    int totalOfAllOrders();
}
