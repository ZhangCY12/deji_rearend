package org.example.dejimanage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

//CNC当前状态实体类
@Data
@TableName("t_cnc_status_time")
public class CncStatusTime {
    @TableId("cnc_num")
    private int cncNum;// id
    private String runTime;//运行时间
    private String idleTime;//待机时间
    private String errorTime;//报警时间
    private String utilizationRate;//稼动率
}
