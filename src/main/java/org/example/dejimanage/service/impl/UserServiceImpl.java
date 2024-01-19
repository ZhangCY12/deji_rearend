package org.example.dejimanage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dejimanage.entity.DTO.UserDTO;
import org.example.dejimanage.entity.User;
import org.example.dejimanage.mapper.UserMapper;
import org.example.dejimanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /***
     * 查询各个部门及其人数
     */
    @Override
    public Map<String,Integer> SelectNumberOfPerson() {

        List<UserDTO> lists = userMapper.SelectNumberTotal();
        Map<String,Integer> maps = new HashMap<>();
        for(UserDTO item : lists){
            maps.put(item.getGroupNames(),item.getCount());
        }
        return maps;
    }
}
