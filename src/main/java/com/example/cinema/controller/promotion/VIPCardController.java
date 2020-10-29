package com.example.cinema.controller.promotion;

import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.vo.VIPCardForm;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPInfoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liying on 2019/4/14.
 * Updated by Steve on 2019/6/10.
 */
@RestController()
@RequestMapping("/vip")
public class VIPCardController {
    @Autowired
    VIPService vipService;

    @PostMapping("/add")
    public ResponseVO addVIP(@RequestParam int userId, @RequestParam String type){
        return vipService.addVIPCard(userId,type);
    }
    @GetMapping("{userId}/get")
    public ResponseVO getVIP(@PathVariable int userId){
        return vipService.getCardByUserId(userId);
    }

    @GetMapping("{userId}/getVIPInfo")
    public ResponseVO getVIPInfo(@PathVariable int userId){
        return vipService.getVIPInfo(userId);
    }

    @PostMapping("/charge")
    public ResponseVO charge(@RequestBody VIPCardForm vipCardForm){
        return vipService.charge(vipCardForm);
    }

    @GetMapping("/getAllVIPInfo")
    public ResponseVO getAllVIPInfo(){
        return vipService.getAllVIPInfo();
    }

    @PostMapping("/publish")
    public ResponseVO publishVIPCard(@RequestBody VIPInfoForm vipInfoForm){
        return vipService.publishVIPCard(vipInfoForm);
    }

    @PostMapping("/modify")
    public ResponseVO modifyVIPCard(@RequestBody VIPInfoForm vipInfoForm){
        return vipService.modifyVIPCard(vipInfoForm);
    }

}
