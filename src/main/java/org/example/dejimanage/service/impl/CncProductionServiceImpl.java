package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dejimanage.entity.CncProduction;
import org.example.dejimanage.mapper.CncProductionMapper;
import org.example.dejimanage.service.CncProductionService;
import org.example.dejimanage.tools.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CncProductionServiceImpl extends ServiceImpl<CncProductionMapper, CncProduction> implements CncProductionService {

    @Autowired
    private CncProductionMapper cncProductionMapper;

    /***
     * 根据id查询单机过去15天的产量
     * @param id 需要查询的id号
     */
    @Override
    public List<Map<String, Object>> getCncProductionByid(int id) {
        List<Map<String,Object>> results = cncProductionMapper.selectProductionByid(id);

        if(results.size() == 15){
            //有15条数据直接返回
            return results;
        }
        else{
            //数据不足则补0
            List<Map<String,Object>> lists = new ArrayList<>();
            Map<String,Object> firstMap = results.get(0);
            Date day = (Date) firstMap.get("date");//得到最新的一天日期
            int Lenght = results.size();
            for (int i = 0; i < 15-Lenght; i++) {
                Map<String,Object> map = new HashMap<>();
                map.put("date", DateUtils.calculateDateBefore(day,15-i));
                map.put("daily_production",0);
                lists.add(map);
            }
            for(int i = Lenght-1; i >= 0; i--){

                Map<String, Object> map = new HashMap<>();
                for (Map.Entry<String, Object> entry : results.get(i).entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if(key.equals("date")){
                        map.put(key,DateUtils.formatDateMonth((Date) value));
                    }else{
                        map.put(key,value);
                    }
                }
                lists.add(map);
            }
            return lists;
        }

    }
}
