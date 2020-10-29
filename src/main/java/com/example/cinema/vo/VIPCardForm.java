package com.example.cinema.vo;

/**
 * Created by liying on 2019/4/14.
 * 用户界面进行会员卡充值与后端交互的VO
 */
public class VIPCardForm {

    /**
     * vip卡id
     */
    private int vipId;

    /**
     *  用户id
     */
    private int userId;

    /**
     * 付款金额
     */
    private int amount;


    public int getVipId() {
        return vipId;
    }

    public void setVipId(int vipId) {
        this.vipId = vipId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
