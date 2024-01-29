package org.example.dejimanage.controller;

import org.example.dejimanage.service.MaintenanceInfoService;
import org.example.dejimanage.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {
    @Autowired
    private MaintenanceInfoService maintenanceInfoService;

    @GetMapping("/info/{id}")
    public Result GetMaintentaceByid(@PathVariable int id){
        return Result.ok().data("maintenace",maintenanceInfoService.getCncMaintenanceByid(id));
    }
}
