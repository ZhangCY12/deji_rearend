package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dejimanage.entity.CncStatusTime;
import org.example.dejimanage.mapper.CncStatusTimeMapper;
import org.example.dejimanage.mapper.DailyOperationMapper;
import org.example.dejimanage.service.CncStatusTimeService;
import org.example.dejimanage.tools.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalTime;
import java.util.*;

@Service
public class CncStatusTimeServiceImpl extends ServiceImpl<CncStatusTimeMapper, CncStatusTime> implements CncStatusTimeService {
    @Autowired
    private CncStatusTimeMapper cncStatusTimeMapper;
    @Autowired
    private DailyOperationMapper dailyOperationMapper;
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
     * @param id 机器号
     */
    @Override
    public Map<String, Object> getCncStatusByid(int id) {
        logger.info("请求(cnc)_查询单机台的稼动率(id::"+id+")");
        return cncStatusTimeMapper.selectCncRateByid(id);
    }

    /***
     *  根据Id查询单机实时运行时间情况
     * @param id 机器号
     * @return 返回小时的数值（例：5.8小时）
     */
    @Override
    public Map<String, Object> getCncRuntimeByid(int id) {
        // 从数据库查询
        CncStatusTime cncStatusTime = cncStatusTimeMapper.selectCncRuntimeByid(id);
        // 将字符串转换成LocalTime对象
        LocalTime runTime =  LocalTime.parse(cncStatusTime.getRunTime());
        LocalTime idleTime = LocalTime.parse(cncStatusTime.getIdleTime());
        LocalTime errorTime = LocalTime.parse(cncStatusTime.getErrorTime());
        // 计算总的活动时间
        long totalMinutes = runTime.toSecondOfDay() +
                idleTime.toSecondOfDay() +
                errorTime.toSecondOfDay();
        // 获取当前时间
        LocalTime now = LocalTime.now() ;
        // 计算离线时间
        // 注意：这里假设活动时间不会超过当前时间，否则需要做额外处理
        long offlineSeconds = 1;
        //判断当前时间是否在0-8、8-20、20-24这3个时段，用来判断白班与夜班
        if(LocalTime.now().isAfter(LocalTime.of(8, 0)) && LocalTime.now().isBefore(LocalTime.of(20, 0))){
            offlineSeconds = now.toSecondOfDay() - totalMinutes - 8 * 60 * 60;
        }else if(LocalTime.now().isAfter(LocalTime.of(20, 0)) && LocalTime.now().isBefore(LocalTime.of(23, 59))){
            offlineSeconds = now.toSecondOfDay() - totalMinutes - 20 * 60 * 60;
        }else{
            offlineSeconds = now.toSecondOfDay() - totalMinutes + 4 * 60 * 60;
        }
        // 如果计算结果为负数，表示活动时间超过了当前时间，可以根据实际需求处理这种情况
        if (offlineSeconds < 0) {
            // 处理逻辑，例如设置为0或者返回特定值
            offlineSeconds = 0;
        }
        //规范数据小数点后1位
        DecimalFormat df = new DecimalFormat("#.0");
        // 将秒数转换为小时
        double offlineHours = offlineSeconds / 3600.0;
        double runTimeHours = runTime.toSecondOfDay() / 3600.0;
        double idleTimeHours = idleTime.toSecondOfDay() / 3600.0;
        double errorTimeHours = errorTime.toSecondOfDay() / 3600.0;

        // 封装到Map中
        Map<String, Object> result = new HashMap<>();

        result.put("runTimeHours",  Double.parseDouble(df.format(runTimeHours)));
        result.put("idleTimeHours", Double.parseDouble(df.format(idleTimeHours)));
        result.put("errorTimeHours", Double.parseDouble(df.format(errorTimeHours)));
        result.put("offlineHours", Double.parseDouble(df.format(offlineHours)));

        logger.info("请求(cnc)_查询单机台实时运行时间情况(id::"+id+")");
        return result;
    }

    /***
     * 根据Id查询历史稼动率信息
     * @param id 机器编号
     * @return 历史稼动率信息
     */
    @Override
    public List<Map<String, Object>> getCncHistoryRateByid(int id) {
        List<Map<String,Object>> results = dailyOperationMapper.selectRuntimeByid(id);
        if (results.isEmpty()){
            return null;
        }
        if(results.size() == 10) {
            //有10条数据直接返回
            List<Map<String,Object>> lists = new ArrayList<>();
            for (int i = 9; i >= 0; i--) {
                Map<String,Object> map = new HashMap<>();
                Map<String,Object> firstMap = results.get(i);
                Date day = (Date) firstMap.get("date");//得到最新的一天日期
                map.put("date", DateUtils.formatDateMonth(day));
                map.put("operation_rate",firstMap.get("operation_rate"));
                lists.add(map);
            }
            return lists;
        }else {
            //数据不足则补0
            List<Map<String,Object>> lists = new ArrayList<>();
            Map<String,Object> firstMap = results.get(0);
            Date day = (Date) firstMap.get("date");//得到最新的一天日期
            int Lenght = results.size();
            for (int i = 0; i < 10-Lenght; i++) {
                Map<String,Object> map = new HashMap<>();
                map.put("date", DateUtils.calculateDateBefore(day,10-i));
                map.put("operation_rate",0.00);
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
