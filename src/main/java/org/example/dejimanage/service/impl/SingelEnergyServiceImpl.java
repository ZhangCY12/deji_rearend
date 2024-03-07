package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dejimanage.entity.SingelEnergy;
import org.example.dejimanage.mapper.SingelEnergyMapper;
import org.example.dejimanage.service.SingelEnergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SingelEnergyServiceImpl extends ServiceImpl<SingelEnergyMapper,SingelEnergy> implements SingelEnergyService {
    @Autowired
    private SingelEnergyMapper singelEnergyMapper;

    @Override
    public List<Map<String, Object>> selectAllSingleEnergy() {
        return singelEnergyMapper.selectALL();
    }
}
