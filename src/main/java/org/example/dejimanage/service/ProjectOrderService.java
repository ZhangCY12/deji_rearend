package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.ProjectOrder;

import java.util.List;
import java.util.Map;

public interface ProjectOrderService extends IService<ProjectOrder> {
    List<ProjectOrder> GetAllProjectOrder(); //查询2024年以来新工单
    Map<String, Integer> getOrderCounts(); //返回各个工单状态的数量（已结束、未开始、加工中、总数）
    List<Map<String, Object>> GetNumPlanPractial(); //查询每个月份的工单计划数和实际数
    void clearProjectCache(); //清除缓存（名：ProjectOrder）中的2024年以来新工单数据
}
