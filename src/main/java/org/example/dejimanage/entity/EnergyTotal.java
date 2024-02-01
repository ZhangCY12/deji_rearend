package org.example.dejimanage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

//能源汇总实体类
@Data
@TableName("t_energy_total")
public class EnergyTotal {
    @TableId("date")
    public String date; //日期
    public String activeEnergyTotal1; //总用电量
    public String humidity1; //湿度
    public String temperature1; //温度
}
