package org.example.dejimanage.controller;

import org.example.dejimanage.service.CncAlarmAllService;
import org.example.dejimanage.service.CncAlarmService;
import org.example.dejimanage.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/CncAlarm")
public class CncAlarmController {

    @Autowired
    private CncAlarmService cncAlarmService;
    @Autowired
    private CncAlarmAllService cncAlarmAllService;

    @GetMapping("/all/{id}")
    public Result GetCncAllAlarms(@PathVariable int id){

        return Result.ok().data("cncsAlarm", cncAlarmService.getCncAllAlarmByid(id));
    }
    @GetMapping("/alarm/{id}")
    public Result GetCncSingleAlarms(@PathVariable int id){

        return Result.ok().data("cncAlarm", cncAlarmAllService.getAlarmHisBuid(id));
    }

}
