package org.example.dejimanage.controller;


import org.example.dejimanage.entity.CncStatusTime;
import org.example.dejimanage.service.CNCsService;
import org.example.dejimanage.service.CncProductionService;
import org.example.dejimanage.service.CncStatusTimeService;
import org.example.dejimanage.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/CNCs")
public class CNCsController {

    @Autowired
    private CncStatusTimeService cncStatusTimeService;
    @Autowired
    private CNCsService cnCsService;
    @Autowired
    private CncProductionService cncProductionService;


    @GetMapping("/time_rate")
    public Result GetAllCncStatusTime(){
        List<CncStatusTime> cncstatus = cncStatusTimeService.getAllCncStatusTime();
        return Result.ok().data("cncstatus", cncstatus);
    }

    @GetMapping("/status")
    public Result GetALLCNCsStatus(){
        List<String> cnCs = cnCsService.getAllCncStatus();
        return Result.ok().data("status", cnCs);
    }

    @GetMapping("/status/number")
    public Result GetALLNumberOfCncStatus(){
        return Result.ok().data("status_number",cnCsService.getALLNumberOfCncStatus());
    }

    @GetMapping("/deviceAndCount")
    public Result GetDeviceAndCount() {
        return Result.ok().data("DeviceAndCount",cnCsService.getDeviceAndCount());
    }

    @GetMapping("/speedmaincut/{id}")
    public Result GetSpeedMainCutByid(@PathVariable int id){
        return Result.ok().data("speed",cnCsService.getSpeedOfMainCut(id));
    }

    @GetMapping("/utilization/{id}")
    public Result GetUtilizationRateByid(@PathVariable int id){
        return Result.ok().data("utilization",cncStatusTimeService.getCncStatusByid(id));
    }

    @GetMapping("/production/{id}")
    public Result GetProductionByid(@PathVariable int id){
        return Result.ok().data("production",cncProductionService.getCncProductionByid(id));
    }
}
