package org.example.dejimanage.controller;

import org.example.dejimanage.service.EnergyTotalService;
import org.example.dejimanage.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/energy")
public class EnergyTotalController {


    @Autowired
    private EnergyTotalService energyTotalService;


    @GetMapping("/all")
    public Result findAllEnergy() {
        return Result.ok().data("energys", energyTotalService.GetAllEnergy());
    }

    @GetMapping("/now")
    public Result findNowEnergy(){
        return Result.ok().data("energy",energyTotalService.GetNowEnergy());
    }
}
