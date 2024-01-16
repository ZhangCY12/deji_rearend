package org.example.dejimanage.controller;


import org.example.dejimanage.entity.EnergyTotal;
import org.example.dejimanage.service.EnergyTotalService;
import org.example.dejimanage.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/energy")
public class EnergyTotalController {


    @Autowired
    private EnergyTotalService energyTotalService;


    @GetMapping("/all")
    public Result findAllEnergy() {
        List<EnergyTotal> energyTotals = energyTotalService.GetAllEnergy();
        for (EnergyTotal energyTotal : energyTotals) {
            energyTotal.date = energyTotal.date.substring(energyTotal.date.length() - 2);
        }
        return Result.ok().data("energys", energyTotals);
    }
}
