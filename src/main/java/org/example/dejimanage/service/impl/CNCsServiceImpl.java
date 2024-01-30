package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dejimanage.entity.CNCs;
import org.example.dejimanage.mapper.CNCsMapper;
import org.example.dejimanage.service.CNCsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CNCsServiceImpl extends ServiceImpl<CNCsMapper, CNCs> implements CNCsService {
    @Autowired
    private CNCsMapper cnCsMapper;
    private static final Logger logger = LoggerFactory.getLogger(CNCsServiceImpl.class);
    /***
     * 查询所有CNC机台的状态
     */
    @Override
    public List<String> getAllCncStatus() {
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
        logger.info("请求(cnc)_查询所有CNC机台状态");
        return message;
    }
    /***
     * 查询cnc机台各个状态和数量
     */
    @Override
    public Map<String, Integer> getALLNumberOfCncStatus() {
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
        logger.info("请求(cnc)_查询cnc机台各个状态和数量");
        return statusCounts;
    }

    /***
     * 查询机台的总类及其数量
     */
    @Override
    public List<Map<String, Object>> getDeviceAndCount() {
        List<Map<String, Object>> lists = cnCsMapper.selectDeviceAndCount();
        Map<String,Object> map = new HashMap<>();
        map.put("name","其他");
        map.put("count",0);
        lists.add(map);
        logger.info("请求(cnc)_查询机台的总类及其数量");
        return lists;
    }

    /***
     * 根据Id查询主轴速度和切削速度
     * @param id 机器号
     */
    @Override
    public Map<String, Object> getSpeedOfMainCut(int id) {
        logger.info("请求(cnc)_查询单机台的主轴速度和切屑速度（id::"+id+")");
        return cnCsMapper.selectSpeedOfMainCut(id);
    }

    /***
     * 插入机器加工的总工件数(产量)
     */
    @Override
    @Transactional
    public void insertAllArtifacts() {
        logger.info("定时任务_插入机器加工的总工件数(产量)");
        cnCsMapper.insertAllArtifacts();
    }

    /***
     * 根据id查询所有信息
     * @param id 机器号
     * @return 所有信息
     */
    @Override
    public Map<String, Object> getAllInfoByid(int id) {
        return cnCsMapper.selectAllByid(id);
    }
}
