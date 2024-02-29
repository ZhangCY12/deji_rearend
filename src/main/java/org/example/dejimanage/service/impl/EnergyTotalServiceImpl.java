package org.example.dejimanage.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dejimanage.entity.EnergyTotal;
import org.example.dejimanage.mapper.EnergyTotalMapper;
import org.example.dejimanage.service.EnergyTotalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EnergyTotalServiceImpl extends ServiceImpl<EnergyTotalMapper, EnergyTotal> implements EnergyTotalService {
    @Autowired
    private EnergyTotalMapper energyTotalMapper;
    private static final Logger logger = LoggerFactory.getLogger(EnergyTotalServiceImpl.class);//日志记录对象
    /**
     * 计算过去10天，每天的平均温湿度，和用电量
     */
    @Override
    public List<Map<String,Object>> GetAllEnergy() {
        List<Map<String,Object>> energyTotals = energyTotalMapper.selectEnergyUsageLastTenDays();
        List<Map<String,Object>> lists = new ArrayList<>();
        //逻辑：当天总电量减去前一天总电量为当天用电量

        for (int i = 0; i < 10; i++){
            Map<String,Object> map = new HashMap<>();
            for (Map.Entry<String, Object> entry : energyTotals.get(i).entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if(entry.getKey().equals("date")){
                    value = value.toString().substring(value.toString().length() - 2);
                }else if(entry.getKey().equals("humidity") || entry.getKey().equals("temperature")){
                    value = String.format("%.1f",(Double.parseDouble(value.toString()) / 3));
                } else{
                    Map<String, Object> selectedMap = energyTotals.get(i+1);
                    Double result = Double.parseDouble(selectedMap.get("activeEnergyTotal").toString());
                    Double value1 = Double.parseDouble(value.toString());
                    value = String.format("%.1f", value1-result);
                }
                // 选择覆盖旧值
                map.put(key, value);
            }
            lists.add(map);
        }
        Collections.reverse(lists);
        logger.info("请求(能源)_查询过去10天，每天的平均温湿度，和用电量");
        return lists;
    }

    /***
     * 查询当天的用电量、温湿度信息
     */
    @Override
    public List<Map<String,Object>> GetNowEnergy() {
        List<Map<String,Object>> energy = energyTotalMapper.selectEnergyNowDay();
        List<Map<String,Object>> result = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> resultmap = energy.get(0);
        map.put("activeEnergyTotal1",resultmap.get("activeEnergyTotal").toString());
        //处理查询数据，保留1位小数
        map.put("humidity1", String.format("%.1f", Double.parseDouble(resultmap.get("humidity").toString())/3));
        map.put("temperature1",String.format("%.1f", Double.parseDouble(resultmap.get("temperature").toString())/3));
        result.add(map);
        logger.info("请求(能源)_当天的用电量、温湿度信息");
        return result;
    }

}
