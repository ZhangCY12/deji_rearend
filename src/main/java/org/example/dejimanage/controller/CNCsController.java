package org.example.dejimanage.controller;


import org.example.dejimanage.entity.CncStatusTime;
import org.example.dejimanage.service.CNCsService;
import org.example.dejimanage.service.CncStatusTimeService;
import org.example.dejimanage.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/CNCs")
public class CNCsController {


    @Autowired
    private CncStatusTimeService cncStatusTimeService;
    @Autowired
    private CNCsService cnCsService;


    @GetMapping("/time_rate")
    public Result GetAllCncStatusTime(){
        List<CncStatusTime> cncstatus = cncStatusTimeService.GetAllCncStatusTime();
        return Result.ok().data("cncstatus", cncstatus);
    }

    @GetMapping("/status")
    public Result GetALLCNCsStatus(){
        List<String> cnCs = cnCsService.GetAllCncStatus();
        return Result.ok().data("status", cnCs);
    }

    @GetMapping("/status/number")
    public Result GetALLNumberOfCncStatus(){
        return Result.ok().data("status_number",cnCsService.GetALLNumberOfCncStatus());
    }

    @GetMapping("/deviceAndCount")
    public Result getDeviceAndCount() {
        return Result.ok().data("DeviceAndCount",cnCsService.getDeviceAndCount());
    }
}
