package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dejimanage.entity.CncAlarmAll;
import org.example.dejimanage.mapper.CNCsMapper;
import org.example.dejimanage.mapper.CncAlarmAllMapper;
import org.example.dejimanage.mapper.CncAlarmMapper;
import org.example.dejimanage.service.CncAlarmAllService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CncAlarmAllServiceImpl extends ServiceImpl<CncAlarmAllMapper, CncAlarmAll> implements CncAlarmAllService {
    @Autowired
    private CNCsMapper cnCsMapper;
    @Autowired
    private CncAlarmMapper cncAlarmMapper;
    @Autowired
    private CncAlarmAllMapper cncAlarmAllMapper;
    private static final Logger logger = LoggerFactory.getLogger(CncAlarmAllServiceImpl.class);


    @Override
    public void recordDailyMachineAlarms() {
        // 获取所有机台的ID
        List<String> allMachineIds = cnCsMapper.selectAllMachineIds();

        // 获取当天每个机台的报警次数
        List<Map<String, Object>> alarmStats = cncAlarmMapper.selectDailyMachineAlarms();

        // 创建一个Map来存储报警次数，以机台ID为键
        Map<String, Integer> alarmCountMap = new HashMap<>();
        for (Map<String, Object> stat : alarmStats) {
            String machineId = stat.get("machine_number").toString();
            Integer alarmCount = Integer.parseInt(stat.get("alarm_count").toString());
            alarmCountMap.put(machineId, alarmCount);
        }

        // 当前日期的java.util.Date实例
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // 对于每个机台，如果有报警记录则使用该记录，否则报警次数为0
        for (String machineId : allMachineIds) {
            CncAlarmAll cncAlarmAll = new CncAlarmAll();
            cncAlarmAll.setMachineId(Integer.parseInt(machineId.substring(machineId.indexOf("_") + 1)));
            cncAlarmAll.setDate(sdf.format(currentDate));
            cncAlarmAll.setAlarmNumber(alarmCountMap.getOrDefault(machineId, 0));
            cncAlarmAllMapper.insert(cncAlarmAll);
        }
        logger.info("定时任务_插入当天机台的所有报警次数");
    }

    /***
     * 根据Id查询历史报警次数信息
     * @param id 机器号
     * @return 报警次数信息
     */
    @Override
    public List<CncAlarmAll> getAlarmHisBuid(int id) {
        int num = id;
        if(id == 52){
            num = 26;
        }
        if(id == 52){
            num = 25;
        }
        List<CncAlarmAll> completeAlarms = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd");

        for (int i = 1; i <= 10; i++) {
            calendar.add(Calendar.DATE, -1);
            Date date = calendar.getTime();

            Integer alarmCount = cncAlarmAllMapper.selectAlarmCountByDate(num, date);
            if (alarmCount == null) {
                alarmCount = 0;
            }
            try {
                Date formattedDate = sdf.parse(sdf.format(date));
                CncAlarmAll cncAlarmAll = new CncAlarmAll();
                cncAlarmAll.setDate(sdf.format(formattedDate));
                cncAlarmAll.setAlarmNumber(alarmCount);
                cncAlarmAll.setMachineId(num);
                completeAlarms.add(cncAlarmAll);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Collections.reverse(completeAlarms); // To have the list in chronological order
        return completeAlarms;
    }

}
