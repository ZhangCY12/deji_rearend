package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dejimanage.entity.EnergyTotal;
import org.example.dejimanage.mapper.EnergyTotalMapper;
import org.example.dejimanage.service.EnergyTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnergyTotalServiceImpl extends ServiceImpl<EnergyTotalMapper, EnergyTotal> implements EnergyTotalService {
    @Autowired
    private EnergyTotalMapper energyTotalMapper;

    /**
     * 计算每天的平均温湿度，和每天的用电量计算
     * @return
     */
    @Override
    public List<EnergyTotal> GetAllEnergy() {
        List<EnergyTotal> energyTotals = new LambdaQueryChainWrapper<>(energyTotalMapper)
                .last("limit 10") // 限制返回的记录数最多为10条
                .list();
        int i = 0;
        List<String> activeEnergyTotals = new ArrayList<>();
        for (EnergyTotal energyTotal : energyTotals) {
            energyTotal.date = energyTotal.date.substring(energyTotal.date.length() - 2);
            activeEnergyTotals.add(energyTotal.activeEnergyTotal);
        }
        for (EnergyTotal energyTotal : energyTotals) {
            if (i == 0) {
                energyTotal.activeEnergyTotal = String.valueOf(Double.valueOf(energyTotal.activeEnergyTotal) - 950).substring(0, 5);
            } else {
                energyTotal.activeEnergyTotal = String.valueOf(Double.valueOf(energyTotal.activeEnergyTotal) - Double.valueOf(activeEnergyTotals.get(i - 1))).substring(0, 5);
            }
            i++;
        }
        return energyTotals;
    }


}
