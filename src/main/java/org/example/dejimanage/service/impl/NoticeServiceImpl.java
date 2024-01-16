package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dejimanage.entity.EnergyTotal;
import org.example.dejimanage.entity.Notice;
import org.example.dejimanage.mapper.NoticeMapper;
import org.example.dejimanage.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public Map<String, String> GetNotice() {
        List<Notice> energyTotals = new LambdaQueryChainWrapper<>(noticeMapper)
                .last("limit 1")
                .orderByDesc(Notice::getCreattime)
                .list();
        Map<String,String> notice = new HashMap<>();
        notice.put("notice",energyTotals.get(0).getContent());
        return notice;
    }
}
