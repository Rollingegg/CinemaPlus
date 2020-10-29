package com.example.cinema.vo;

import com.example.cinema.po.Activity;
import com.example.cinema.po.Coupon;

import java.util.List;

/**
 * Created by liying on 2019/4/21.
 * Updated by zhihao li on 2019/5/18.
 */
public class TicketWithCouponVO {

    private List<TicketVO> ticketVOList;

    private double total;

    private List<CouponVO> coupons;

    private List<ActivityVO> activities;

    public List<ActivityVO> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityVO> activities) {
        this.activities = activities;
    }

    public List<TicketVO> getTicketVOList() {
        return ticketVOList;
    }

    public void setTicketVOList(List<TicketVO> ticketVOList) {
        this.ticketVOList = ticketVOList;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public List<CouponVO> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<CouponVO> coupons) {
        this.coupons = coupons;
    }
}
