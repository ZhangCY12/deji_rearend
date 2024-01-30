package org.example.dejimanage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

//维修保养信息实体
@Data
@TableName("t_cnc_maintenance")
public class MaintenanceInfo {
    @TableId("machine_id")
    private int machineId; //机器信息
    private Date lastTime; //上次保养时间
    private Date nextTime; //下次保养时间
    private Date lastRepairtime; //上次维修时间
}
