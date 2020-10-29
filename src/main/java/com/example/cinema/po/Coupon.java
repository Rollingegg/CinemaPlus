package com.example.cinema.po;

import com.example.cinema.vo.CouponVO;

import java.sql.Timestamp;

/**
 * Created by liying on 2019/4/16.
 * Updated by zhihao li on 2019/5/18.
 */
public class Coupon {
    /**
     * 优惠券id
     */
    private int id;
    /**
     * 优惠券描述
     */
    private String description;
    /**
     * 优惠券名称
     */
    private String name;
    /**
     * 优惠券使用门槛
     */
    private double targetAmount;
    /**
     * 优惠券优惠金额
     */
    private double discountAmount;
    /**
     * 可用时间
     */
    private Timestamp startTime;
    /**
     * 失效时间
     */
    private Timestamp endTime;

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Coupon() {
    }

    public CouponVO getVO() {
        CouponVO couponVO = new CouponVO();
        couponVO.setId(this.id);
        couponVO.setDescription(this.description);
        couponVO.setDiscountAmount(this.discountAmount);
        couponVO.setName(this.name);
        couponVO.setStartTime(this.startTime);
        couponVO.setEndTime(this.endTime);
        couponVO.setTargetAmount(this.targetAmount);
        return couponVO;
    }
}
