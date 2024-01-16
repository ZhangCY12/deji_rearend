package org.example.dejimanage.controller;

import org.example.dejimanage.service.ProjectOrderService;
import org.example.dejimanage.tools.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectOrderController {

    @Autowired
    private ProjectOrderService projectOrderService;

    @GetMapping("/all")
    public Result GetAllProjectOrder(){
        return Result.ok().data("order", projectOrderService.GetAllProjectOrder());
    }

    @GetMapping("/variousNumber")
    public Result GetNumberOfVariousTypesOfWorkOrders(){
        return Result.ok().data("orderType",projectOrderService.getOrderCounts());
    }
}
