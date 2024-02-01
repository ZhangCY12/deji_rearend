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
    private int machineId; //机器号
    private Date date; //日期
    private String operationRate; //稼动率
    private String runTime; //运行时间
    private String standbyTime; //待机时间
    private String errorTime; //报警时间
}
