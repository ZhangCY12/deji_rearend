package org.example.dejimanage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

//能源汇总实体类
@Data
@TableName("t_energy_total")
public class EnergyTotal {
    @TableId("date")
    private String date; //日期
    private String activeEnergyTotal1; //总用电量
    private String humidity1; //湿度
    private String temperature1; //温度
    private String activeEnergy1;
    private String activeEnergyTotal2;
    private String humidity2;
    private String temperature2;
    private String activeEnergy2;
    private String activeEnergyTotal3;
    private String humidity3;
    private String temperature3;
    private String activeEnergy3;
}
