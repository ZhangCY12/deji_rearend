package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dejimanage.entity.ProjectOrder;
import org.example.dejimanage.mapper.ProjectOrderMapper;
import org.example.dejimanage.service.ProjectOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class ProjectOrderServiceImpl extends ServiceImpl<ProjectOrderMapper, ProjectOrder> implements ProjectOrderService {

    @Autowired
    private ProjectOrderMapper projectOrderMapper;

    /***
     * 查询2024年以来新工单
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
    /***
     * 查询每个月份的工单计划数和实际数
     */
    @Override
    public List<Map<String, Object>> GetNumPlanPractial() {
        List<Map<String, Object>> listPlan = projectOrderMapper.countOrdersByMonthPlan();
        List<Map<String, Object>> listPractial = projectOrderMapper.countOrdersByMonthPractial();
        List<Map<String, Object>> reslut = new ArrayList<>();
        for (int i = listPlan.size() - 1; i >= 0; i--) {
            Map<String, Object> map = new HashMap<>();
            // 遍历第二个列表的Map，添加其元素到组合Map中
            for (Map.Entry<String, Object> entry : listPractial.get(i).entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                // 如果key在第一个Map中已存在，你可以选择覆盖它，或者合并值
                // 此例中，我们选择覆盖旧值
                map.put(key, value);
            }
            // 将第一个列表的Map元素添加到组合Map中
            map.putAll(listPlan.get(i));

            reslut.add(map);
        }
        return reslut;
    }
}
