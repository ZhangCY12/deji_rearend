package org.example.dejimanage.controller;

import org.example.dejimanage.service.NoticeService;
import org.example.dejimanage.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/now")
    public Result GetNotice(){

        return Result.ok().data("notice",noticeService.GetNotice());
    }
}
