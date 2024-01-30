package org.example.dejimanage.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.sql.Date;

//CNC每日稼动率和运行时间实体类
@Data
@TableName("t_cnc_daily_operation")
public class DailyOperation {
    @TableId("id")
    private int id;
    private int machineId;
    private Date date;
    private String operationRate;
    private String runTime;
    private String standbyTime;
    private String errorTime;
}
