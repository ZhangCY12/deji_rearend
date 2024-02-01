package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dejimanage.entity.CncAlarm;
import org.example.dejimanage.mapper.CncAlarmMapper;
import org.example.dejimanage.service.CncAlarmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CncAlarmServiceImpl extends ServiceImpl<CncAlarmMapper, CncAlarm> implements CncAlarmService {

    @Autowired
    private CncAlarmMapper cncAlarmMapper;
    private static final Logger logger = LoggerFactory.getLogger(CncAlarmServiceImpl.class);

    /***
     * 根据id查询当天的报警
     * @param id 机器号
     * @return 对象列表
     */
    @Override
    public List<CncAlarm> getCncAllAlarmByid(int id) {
        String cnc_no = "CNC_" + id;
        // 从数据库获取当天的报警信息
        List<CncAlarm> alarms = cncAlarmMapper.selectTodayAlarms(cnc_no);
        logger.info("请求_根据Id查询当天的报警信息（id::"+id+")");
        // 使用正则表达式提取每个报警信息中的中文字符
        Pattern pattern = Pattern.compile("[\\u4e00-\\u9fa5]+");
        return alarms.stream().map(cncAlarm -> {
            Matcher matcher = pattern.matcher(cncAlarm.getAlarm());
            StringBuilder chinesePart = new StringBuilder();
            while (matcher.find()) {
                chinesePart.append(matcher.group());
            }
            cncAlarm.setAlarm(chinesePart.toString());
            return cncAlarm;
        }).collect(Collectors.toList());
    }
}
