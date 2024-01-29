package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dejimanage.entity.ProjectOrder;
import org.example.dejimanage.mapper.ProjectOrderMapper;
import org.example.dejimanage.service.ProjectOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class ProjectOrderServiceImpl extends ServiceImpl<ProjectOrderMapper, ProjectOrder> implements ProjectOrderService {

    @Autowired
    private ProjectOrderMapper projectOrderMapper;
    private static final Logger logger = LoggerFactory.getLogger(ProjectOrderServiceImpl.class);//日志对象

    /***
     * 查询2024年以来新工单
     */
    @Override
    @Cacheable(value = "ProjectOrder")//配置redis缓存
    public List<ProjectOrder> GetAllProjectOrder() {
        logger.info("请求_查询2024年以来新工单");
        List<ProjectOrder> lists = projectOrderMapper.selectOrdersFrom2024();
        for(ProjectOrder item :lists){
            item.planamount = item.planamount.split("\\.")[0];
            item.numberofgoodproducts = item.numberofgoodproducts.split("\\.")[0];
            item.numberofdefectives = item.numberofdefectives.split("\\.")[0];
            item.actualamount = item.actualamount.split("\\.")[0];
        }
        logger.info("redis_存入2024年以来新工单进入缓存");
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
        logger.info("请求(工单)_查询各个工单状态的数量（已结束、未开始、加工中、总数）");
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
                // 选择覆盖旧值
                map.put(key, value);
            }
            // 将第一个列表的Map元素添加到组合Map中
            map.putAll(listPlan.get(i));
            reslut.add(map);
        }
        logger.info("请求(工单)_查询每个月份的工单计划数和实际数");
        return reslut;
    }

    /***
     * 清除缓存（名：ProjectOrder）中的2024年以来新工单数据
     */
    @CacheEvict(value = "ProjectOrder", allEntries = true)
    public void clearProjectCache() {
        // 清除ProjectOrder缓存
        logger.info("定时任务_redis_清理2024年以来新工单缓存");
    }
}
