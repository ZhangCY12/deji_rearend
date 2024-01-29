package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dejimanage.entity.CncStatusTime;
import org.example.dejimanage.mapper.CncStatusTimeMapper;
import org.example.dejimanage.service.CncStatusTimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CncStatusTimeServiceImpl extends ServiceImpl<CncStatusTimeMapper, CncStatusTime> implements CncStatusTimeService {
    @Autowired
    private CncStatusTimeMapper cncStatusTimeMapper;
    private static final Logger logger = LoggerFactory.getLogger(CncStatusTimeServiceImpl.class);

    /***
     * 查询所有cnc机台的稼动率、运行时间、待机时间、异常时间
     */
    @Override
    public List<CncStatusTime> getAllCncStatusTime(){
        QueryWrapper<CncStatusTime> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("cnc_num");
        logger.info("请求(cnc)_查询所有cnc机台的稼动率、运行时间、待机时间、异常时间");
        return cncStatusTimeMapper.selectList(queryWrapper);
    }

    /***
     * 根据id查询单台机的实时稼动率
     * @param id
     */
    @Override
    public Map<String, Object> getCncStatusByid(int id) {
        logger.info("请求(cnc)_查询单机台的稼动率(id::"+id+")");
        return cncStatusTimeMapper.selectCncRateByid(id);
    }
}
