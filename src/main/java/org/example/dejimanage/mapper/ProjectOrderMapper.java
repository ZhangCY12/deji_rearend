package org.example.dejimanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.dejimanage.entity.ProjectOrder;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectOrderMapper extends BaseMapper<ProjectOrder> {

    /***
     * 查询2024年所有的工单
     */
    @Select("SELECT * " +
            "FROM t_liteweb_project " +
            "WHERE STR_TO_DATE(createtime, '%Y-%m-%d %H:%i:%s') >= '2024-01-01' ")
    List<ProjectOrder> selectOrdersFrom2024();

    /***
     * 查询2024年已完成的工单数量
     */
    @Select("SELECT COUNT(*) " +
            "FROM t_liteweb_project " +
            "WHERE actualstarttime != '' " +
            "AND actualendtime != '' " +
            "AND STR_TO_DATE(createtime, '%Y-%m-%d %H:%i:%s') >= '2024-01-01'")
    int countCompletedOrders();

    /***
     * 查询2024年加工中的工单数量
     */
    @Select("SELECT COUNT(*) " +
            "FROM t_liteweb_project " +
            "WHERE actualstarttime != '' " +
            "AND actualendtime = '' " +
            "AND STR_TO_DATE(createtime, '%Y-%m-%d %H:%i:%s') >= '2024-01-01'")
    int countProcessingOrders();

    /***
     * 查询2024年来未开始的工单数量
     */
    @Select("SELECT COUNT(*) " +
            "FROM t_liteweb_project " +
            "WHERE actualstarttime = '' " +
            "AND actualendtime = '' " +
            "AND STR_TO_DATE(createtime, '%Y-%m-%d %H:%i:%s') >= '2024-01-01'")
    int countNotStartedOrders();

    /***
     * 查询2024年所有工单总数
     */
    @Select("SELECT COUNT(*) " +
            "FROM t_liteweb_project " +
            "WHERE STR_TO_DATE(createtime, '%Y-%m-%d %H:%i:%s') >= '2024-01-01'")
    int totalOfAllOrders();

    /***
     * 查询每个月份的计划数量
     */
    @Select("SELECT  YEAR(STR_TO_DATE(planstarttime, '%Y-%m-%d')) as year, MONTH(STR_TO_DATE(planstarttime, '%Y-%m-%d')) as month, COUNT(*) as Plancount " +
            "FROM t_liteweb_project " +
            "WHERE planstarttime IS NOT NULL AND planstarttime != '' " +
            "GROUP BY year, month " +
            "ORDER BY year DESC, month DESC")
    List<Map<String, Object>> countOrdersByMonthPlan();

    /***
     * 查询每个月份的实际数量
     */
    @Select("SELECT  YEAR(STR_TO_DATE(actualendtime, '%Y-%m-%d')) as year, MONTH(STR_TO_DATE(actualendtime, '%Y-%m-%d')) as month, COUNT(*) as Practialcount " +
            "FROM t_liteweb_project " +
            "WHERE actualendtime IS NOT NULL AND actualendtime != '' AND MONTH(STR_TO_DATE(planstarttime, '%Y-%m-%d')) = MONTH(STR_TO_DATE(actualendtime, '%Y-%m-%d'))" +
            "GROUP BY year, month " +
            "ORDER BY year DESC, month DESC")
    List<Map<String, Object>> countOrdersByMonthPractial();
}
