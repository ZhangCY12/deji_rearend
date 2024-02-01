package org.example.dejimanage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_cnc_alarm_total")
public class CncAlarmAll {
    @TableId("id")
    private int id;
    private int machineId;
    private String date;
    private int alarmNumber;
}
