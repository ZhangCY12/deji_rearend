package org.example.dejimanage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_cnc_classes_energy")
public class SingelEnergy {
    @TableId("id")
    private int id;
    private Date date;
    private int machineId;
    private String classes;
    private double energy;
}
