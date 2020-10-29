package com.example.cinema.blImpl.promotion;
import com.example.cinema.po.Coupon;
import com.example.cinema.vo.CouponVO;

import java.util.List;

/**
 * @author zhihao li
 * @date 2019/5/18 12:10 AM
 */
public interface CouponServiceForBl {
    /**
     * 根据userId查询优惠券
     * @param userId
     * @return
     */
    List<CouponVO> getCouponByUser(int userId);

    List<CouponVO> getCouponByUserAndAmount(int useId, double amount);

    CouponVO getCouponById(int couponId);

    void deleteCouponUser(int couponId, int userId);

    void insertCouponUser(int couponId, int userId);

    void insertCoupon(Coupon coupon);
}
