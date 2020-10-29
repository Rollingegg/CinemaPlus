package com.example.cinema.vo;

import java.sql.Timestamp;
import java.util.List;

public class ConsumptionDetailsVO {

    //消费记录的id
    private int id;

    //用户id
    private int userId;

    //电影票总价
    private double total;

    //实际支付
    private double pay;

    //折扣
    private double discountAmount;

    //支付方式，0为会员卡,1为银行卡
    private String payMethod;

    //涉及到的电影票
    private List<TicketWithScheduleVO> ticketList;

    //使用的优惠券
    private CouponVO couponVO;

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

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public List<TicketWithScheduleVO> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<TicketWithScheduleVO> ticketList) {
        this.ticketList = ticketList;
    }

    public CouponVO getCouponVO() {
        return couponVO;
    }

    public void setCouponVO(CouponVO couponVO) {
        this.couponVO = couponVO;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }
}
