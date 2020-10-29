package com.example.cinema.blImpl.promotion;

import com.example.cinema.po.VIPCard;

public interface VIPServiceForBl {
    VIPCard getVIPCardByUserId(int id);
    boolean updateVIPCardBalance(int id, double amount);

    /**
     *  获取用户会员卡余额
     * @param id 用户id
     * @return
     */
    double getBalance(int id);

    double getVipDiscountByUserId(int id);
}
