package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dejimanage.entity.CncStatusTime;
import org.example.dejimanage.mapper.CncClassesMapper;
import org.example.dejimanage.mapper.CncStatusTimeMapper;
import org.example.dejimanage.service.CncStatusTimeService;
import org.example.dejimanage.tools.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalTime;
import java.util.*;

@Service
public class CncStatusTimeServiceImpl extends ServiceImpl<CncStatusTimeMapper, CncStatusTime> implements CncStatusTimeService {
    @Autowired
    private CncStatusTimeMapper cncStatusTimeMapper;
    @Autowired
    private CncClassesMapper cncClassesMapper;
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
     * 根据ID查询单机稼动率
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
        LocalTime runTime =  LocalTime.parse(cncStatusTime.getRunTime()); //运行时间
        LocalTime idleTime = LocalTime.parse(cncStatusTime.getIdleTime()); //待机时间
        LocalTime errorTime = LocalTime.parse(cncStatusTime.getErrorTime()); //报警时间
        // 计算总的活动时间
        long totalMinutes = runTime.toSecondOfDay() +
                idleTime.toSecondOfDay() +
                errorTime.toSecondOfDay();
        // 获取当前时间
        LocalTime now = LocalTime.now() ;
        // 计算离线时间
        // 注意：这里假设活动时间不会超过当前时间，否则需要做额外处理
        long offlineSeconds;
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
     * 根据Id查询历史稼动率信息(由每天白班和夜班数据统计出)
     * @param id 机器编号
     * @return 历史稼动率信息
     */
    @Override
    public List<Map<String, Object>> getCncHistoryRateByid(int id){
        List<Map<String,Object>> results = cncClassesMapper.selectCncClassesByid(id);
        List<Map<String,Object>> lists = new ArrayList<>();
        try{
            for(int i = results.size() - 1;i >= 0; i-=2){
                Map<String,Object> map = new HashMap<>();
                Map<String, Object> entryDay = results.get(i-1);
                Map<String, Object> entryNight = results.get(i);
                String rateDay = (String) entryDay.get("operation_rate"); //白班稼动率
                String rateNight = (String) entryNight.get("operation_rate"); //夜班稼动率
                Date date = (Date) entryDay.get("date"); //日期
                // 去除百分号，并将字符串转换为浮点数
                double number1 = parsePercentage(rateDay);
                double number2 = parsePercentage(rateNight);
                // 计算平均稼动率
                double average = (number1 + number2) / 2;

                //将处理后的信息添加到map中
                map.put("date", DateUtils.formatDateMonthDay(date));
                map.put("rateDay",truncateAndMultiply(number1));
                map.put("rateNight",truncateAndMultiply(number2));
                map.put("rateAgv",truncateAndMultiply(average));

                lists.add(map);
            }
        }
        catch (ParseException e){
            System.out.println(e);
        }
        logger.info("请求(cnc)_查询单机台历史白夜班稼动率(id::"+id+")");
        return lists;
    }

    /***
     * 将百分数字符串转换为浮点数
     * @param percentage 百分数字符串（23.23%）
     * @return double类型数字（23.23）
     */
    private static double parsePercentage(String percentage) throws ParseException {
        NumberFormat format = NumberFormat.getPercentInstance();
        Number number = format.parse(percentage);
        return number.doubleValue();
    }

    /***
     * 把多位0-1的小数转换为保留4位的百分数
     * @param num 小数（0.2314151231231）
     * @return 字符串百分数（23.14%）
     */
    public static double truncateAndMultiply(double num) {
        // 使用 BigDecimal 处理精度问题
        BigDecimal bd = new BigDecimal(Double.toString(num));
        // 保留四位小数，并且使用 RoundingMode.HALF_UP 进行四舍五入
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        // 乘以 100
        bd = bd.multiply(new BigDecimal("100"));
        // 转换为 double 类型并返回
        return bd.doubleValue();
    }
}
