package org.example.dejimanage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_energy_total")
public class EnergyTotal {
    @TableId("date")
    public String date;//日期
    public String activeEnergyTotal;//总用电量
    public String humidity;//湿度
    public String temperature;//温度
}
