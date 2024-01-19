package org.example.dejimanage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.dejimanage.entity.User;

import java.util.Map;

public interface UserService extends IService<User> {
    Map<String,Integer> SelectNumberOfPerson();
}
