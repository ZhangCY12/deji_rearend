package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.Notice;

import java.util.Map;

public interface NoticeService extends IService<Notice> {
    public Map<String,String> GetNotice();
}
