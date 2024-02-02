package org.example.dejimanage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_cnc_deviceprofile")
public class DeviceProfile {
    @TableId("machine_id")
    private int machineId;
    private String assetNumber;
    private String useDepartments;
    private String user;
    private String storageLocation;
    private Date gradeDate;
    private String name;
}
