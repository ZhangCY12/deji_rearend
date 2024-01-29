package org.example.dejimanage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_cnc_maintenance")
public class MaintenanceInfo {
    @TableId("machine_id")
    private int machineId;
    private Date lastTime;
    private Date nextTime;
    private Date lastRepairtime;
}
