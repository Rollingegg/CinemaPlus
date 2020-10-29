package com.example.cinema.po;

import java.sql.Timestamp;
import java.util.List;

public class ConsumptionItem {

    //消费记录的id
    private int id;

    //用户id
    private int userId;

    //电影票总价
    private double total;

    //折扣
    private double discountAmount;

    //支付方式，0为会员卡,1为银行卡
    private int payMethod;

    //涉及到的电影票
    private List<Integer> ticketId;

    //使用的优惠券id
    private int couponId;

    //完成支付/回款的时间
    private Timestamp time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(int payMethod) {
        this.payMethod = payMethod;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public List<Integer> getTicketId() {
        return ticketId;
    }

    public void setTicketId(List<Integer> ticketId) {
        this.ticketId = ticketId;
    }
}
