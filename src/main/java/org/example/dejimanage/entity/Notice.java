package org.example.dejimanage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

//公告实体类
@Data
@TableName("t_message")
public class Notice {
    private int id;
    private String content; //公告内容
    private LocalDateTime creattime; //创建时间
}
