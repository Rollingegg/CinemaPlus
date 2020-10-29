package com.example.cinema.controller.sales;

import com.example.cinema.bl.sales.ConsumptionService;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumption")
public class ConsumptionController {

    @Autowired
    ConsumptionService consumptionService;

    @GetMapping("/{userId}")
    public ResponseVO getConsumptionByUserId(@PathVariable int userId){
        return consumptionService.getConsumptionByUserId(userId);
    }

    @GetMapping("/get/{consumptionId}")
    public ResponseVO getConsumptionDetails(@PathVariable("consumptionId") int consumptionId){
        return consumptionService.getConsumptionDetails(consumptionId);
    }
}
