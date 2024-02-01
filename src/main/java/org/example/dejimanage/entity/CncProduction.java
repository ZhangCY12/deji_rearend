package org.example.dejimanage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

//CNC产量实体类
@Data
@TableName("t_cnc_production")
public class CncProduction {
    @TableId("id")
    private int id;
    private int maichineId; //机器号
    private Date date; //日期
    private int dailyProduction; //日产量
    private int  totalProduction; //总产量
}
