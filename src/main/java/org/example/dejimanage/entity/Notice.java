package org.example.dejimanage.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("t_message")
public class Notice {
    private int id;
    private String content;
    private LocalDateTime creattime;
}
