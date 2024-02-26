package org.example.dejimanage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.util.Date;

@Data
@TableName("t_cnc_classes_operation")
public class CncClasses {
    @TableId("id")
    private int id;
    private int machineId;
    private Date date;
    private String classes;
    private String operationRate;
    private String runTime;
    private String standbyTime;
    private String errorTime;
}
