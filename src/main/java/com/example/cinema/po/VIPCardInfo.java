/**
 * @Author Administrator
 * @Time 2019/6/10 21:01
 */

package com.example.cinema.po;

import com.example.cinema.vo.VIPInfoVO;

public class VIPCardInfo {
    /**
     * 会员卡id
     */
    private int id;

    /**
     * 会员卡种类
     */
    private String type;

    /**
     * 会员卡购票折扣
     */
    private double discount;

    /**
     * 会员卡充值优惠：满targetAmount送bonusAmount
     */
    private int targetAmount;
    private int bonusAmount;

    /**
     * 会员卡售价
     */
    private double price;

    public VIPInfoVO getVO(){
        VIPInfoVO vo = new VIPInfoVO();
        vo.setDescription(getDescription());
        vo.setPrice(getPrice());
        vo.setType(getType());
        vo.setDiscount(getDiscount());
        return vo;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(int targetAmount) {
        this.targetAmount = targetAmount;
    }

    public int getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(int bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return "满" + targetAmount + "送" + bonusAmount;
    }

    public double calculate(double amount) {
        if(targetAmount==0){
            return amount+bonusAmount;
        }
        return (int)(amount/targetAmount)*bonusAmount+amount;
    }
}
