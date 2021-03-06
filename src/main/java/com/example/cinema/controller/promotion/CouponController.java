package com.example.cinema.controller.promotion;

import com.example.cinema.bl.promotion.CouponService;
import com.example.cinema.vo.CouponVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liying on 2019/4/16.
 */
@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    CouponService couponService;

    @GetMapping("{userId}/get")
    public ResponseVO getCoupons(@PathVariable int userId){
        return couponService.getCouponsByUser(userId);
    }
    
    @GetMapping("/valid")
    public ResponseVO getAllValidCoupons() {
        return couponService.getValidCouponList();
    }

    @GetMapping("/issue")
    public ResponseVO issueCoupon(@RequestParam int couponId, @RequestParam int userId) {
        return couponService.issueCoupon(couponId, userId);
    }

}
