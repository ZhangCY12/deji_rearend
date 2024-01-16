package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dejimanage.entity.ProjectOrder;
import org.example.dejimanage.mapper.ProjectOrderMapper;
import org.example.dejimanage.service.ProjectOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class ProjectOrderServiceImpl extends ServiceImpl<ProjectOrderMapper, ProjectOrder> implements ProjectOrderService {

    @Autowired
    private ProjectOrderMapper projectOrderMapper;

    /***
     * 查询2024年以来新工单
     * @return
     */
    @Override
    public List<ProjectOrder> GetAllProjectOrder() {
        List<ProjectOrder> lists = projectOrderMapper.selectOrdersFrom2024();
        for(ProjectOrder item :lists){

            item.planamount = item.planamount.split("\\.")[0];
            item.numberofgoodproducts = item.numberofgoodproducts.split("\\.")[0];
            item.numberofdefectives = item.numberofdefectives.split("\\.")[0];
            item.actualamount = item.actualamount.split("\\.")[0];
        }
        return lists;
    }

    /***
     * 返回各个工单状态的数量（已结束、未开始、加工中、总数）
     * @return
     */
    @Override
    public Map<String, Integer> getOrderCounts() {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("completed", projectOrderMapper.countCompletedOrders());
        counts.put("processing", projectOrderMapper.countProcessingOrders());
        counts.put("notStarted", projectOrderMapper.countNotStartedOrders());
        counts.put("totalOrders",projectOrderMapper.totalOfAllOrders());
        return counts;
    }
}
