package com.example.cinema.vo;


import com.example.cinema.po.VIPCardInfo;

/**
 * Created by liying on 2019/4/15.
 */
public class VIPInfoVO {

    String description;

    double price;

    double discount;

    String type;

    public VIPInfoVO(VIPCardInfo vipCardInfo){
        setDescription(vipCardInfo.getDescription());
        setDiscount(vipCardInfo.getDiscount());
        setType(vipCardInfo.getType());
        setPrice(vipCardInfo.getPrice());
    }
    public VIPInfoVO(){}

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
