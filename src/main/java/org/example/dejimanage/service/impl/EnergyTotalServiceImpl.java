package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dejimanage.entity.EnergyTotal;
import org.example.dejimanage.mapper.EnergyTotalMapper;
import org.example.dejimanage.service.EnergyTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EnergyTotalServiceImpl extends ServiceImpl<EnergyTotalMapper, EnergyTotal> implements EnergyTotalService {
    @Autowired
    private EnergyTotalMapper energyTotalMapper;

    /**
     * 计算过去10天，每天的平均温湿度，和用电量
     * @return
     */
    @Override
    public List<Map<String,String>> GetAllEnergy() {
        List<EnergyTotal> energyTotals = new LambdaQueryChainWrapper<>(energyTotalMapper)
                .orderByDesc(EnergyTotal::getDate)
                .last("limit 11") // 限制返回的记录数最多为11条
                .list();
        List<Map<String,String>> lists = new ArrayList<>();
        //逻辑：当天总电量减去前一天总电量为当天用电量
        for (int i = energyTotals.size() - 2; i >=0; i--) {
            Map<String,String> map = new HashMap<>();
            double nowEle = Double.parseDouble(energyTotals.get(i).activeEnergyTotal);
            double yesterdayEle = Double.parseDouble(energyTotals.get(i+1).activeEnergyTotal);
            map.put("activeEnergyTotal",(nowEle-yesterdayEle)+"");
            map.put("date",energyTotals.get(i).date.substring(energyTotals.get(i).date.length() - 2));
            map.put("humidity",energyTotals.get(i).humidity);
            map.put("temperature",energyTotals.get(i).temperature);
            lists.add(map);
        }
        return lists;
    }

    @Override
    public List<EnergyTotal> GetNowEnergy() {
        List<EnergyTotal> energyTotals = new LambdaQueryChainWrapper<>(energyTotalMapper)
                .last("limit 1")
                .orderByDesc(EnergyTotal::getDate)
                .list();
        return energyTotals;
    }


}
