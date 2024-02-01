package org.example.dejimanage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_cnc_alarm")
public class CncAlarm {
    @TableId("ID")
    private int ID;
    private String time;
    private String device;
    private String alarm;
}
