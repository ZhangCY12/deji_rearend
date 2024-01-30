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
    private int maichineId;
    private Date date;
    private int dailyProduction;
    private int totalProduction;
}
