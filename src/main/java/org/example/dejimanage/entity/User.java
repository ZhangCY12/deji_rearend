package org.example.dejimanage.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

//员工实体类
@Data
@TableName("t_liteweb_user")
public class User {
    @TableId("id")
    private String id;
    private String username;
    private String name;
    private String phone;
    private String roles;
    private String groupNames;
    private String fake;
    private String customfieldvalues;
}
