package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dejimanage.entity.CNCs;
import org.example.dejimanage.mapper.CNCsMapper;
import org.example.dejimanage.service.CNCsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CNCsServiceImpl extends ServiceImpl<CNCsMapper, CNCs> implements CNCsService {
    @Autowired
    private CNCsMapper cnCsMapper;

    /***
     * 查询所有CNC机台的状态
     */
    @Override
    public List<String> GetAllCncStatus() {
        LambdaQueryWrapper<CNCs> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(CNCs::getCncNum);
        queryWrapper.select(CNCs::getCncNum, CNCs::getOnlineStatus, CNCs::getCncStatus);
        List<CNCs> lists = cnCsMapper.selectList(queryWrapper);
        List<String> message = new ArrayList<>();
        for (CNCs item : lists) {
            if (item.onlineStatus.equals("离线")) {
                item.cncStatus = "离线";
            }
            message.add(item.cncNum + " " + item.cncStatus);
        }
        return message;
    }
    /***
     * 查询cnc机台各个状态和数量
     */
    @Override
    public Map<String, Integer> GetALLNumberOfCncStatus() {
        List<Map<String, Object>> results = cnCsMapper.countByStatus();
        // 查询不在线的机器数量
        QueryWrapper<CNCs> queryWrapperOffline = new QueryWrapper<>();
        queryWrapperOffline.eq("online_status", "离线");
        int offlineCount = cnCsMapper.selectCount(queryWrapperOffline);

        //查询总数量
        QueryWrapper<CNCs> queryWrapperAllnumber = new QueryWrapper<>();
        int allNumber = cnCsMapper.selectCount(queryWrapperAllnumber);

        Map<String, Integer> statusCounts = new HashMap<>();
        for (Map<String, Object> result : results) {
            String status = (String) result.get("cnc_status");
            Integer count = ((Long) result.get("count")).intValue();
            statusCounts.put(status, count);
        }
        statusCounts.put("离线",offlineCount);
        statusCounts.put("总数",allNumber);
        return statusCounts;

    }

    /***
     * 查询机器的总类及其数量
     */
    @Override
    public List<Map<String, Object>> getDeviceAndCount() {
        List<Map<String, Object>> lists = cnCsMapper.selectDeviceAndCount();
        Map<String,Object> map = new HashMap<>();
        map.put("name","其他");
        map.put("count",0);
        lists.add(map);
        return lists;
    }
}
