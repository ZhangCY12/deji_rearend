package org.example.dejimanage.controller;

import org.example.dejimanage.service.UserService;
import org.example.dejimanage.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public Result GetAllUser(){
        return Result.ok().data("user",userService.SelectNumberOfPerson());
    }
}
