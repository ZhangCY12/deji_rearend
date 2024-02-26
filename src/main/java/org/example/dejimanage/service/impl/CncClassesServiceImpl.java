package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dejimanage.entity.CncClasses;
import org.example.dejimanage.mapper.CncClassesMapper;
import org.example.dejimanage.service.CncClassesService;
import org.springframework.stereotype.Service;

@Service
public class CncClassesServiceImpl extends ServiceImpl<CncClassesMapper, CncClasses> implements CncClassesService {
}
