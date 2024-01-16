package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.ProjectOrder;

import java.util.List;
import java.util.Map;

public interface ProjectOrderService extends IService<ProjectOrder> {
    public List<ProjectOrder> GetAllProjectOrder();
    public Map<String, Integer> getOrderCounts();
}
