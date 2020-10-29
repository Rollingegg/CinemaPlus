/**
 * @Author Administrator
 * @Time 2019/6/10 16:50
 * 管理员界面发布会员卡与后端交互的 VO
 */

package com.example.cinema.vo;

public class VIPInfoForm {
    /**
     *  type 会员卡种类(如普通卡，白银卡，铂金卡，钻石卡，至尊卡等）
     */
    private String type;
    /**
     *  充值优惠：满 targetAmount 赠 bonusAmount
     */
    private Integer targetAmount;
    private Integer bonusAmount;
    /**
     * price 会员卡购买价格
     */
    private Double price;
    /**
     *  discount 会员卡购票的折扣
     */
    private Double discount;

    /**
     *  转化为VIPInfoVO
     * @return 对应的VIPInfoVO
     */
    public VIPInfoVO toInfoVO(){
        VIPInfoVO vo = new VIPInfoVO();
        vo.setPrice(price);
        String description = "满"+targetAmount+"送"+bonusAmount;
        vo.setDescription(description);
        return vo;
    }

    public Integer getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(Integer targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Integer getBonusAmount() {
        return bonusAmount;
    }

    public void setBonusAmount(Integer bonusAmount) {
        this.bonusAmount = bonusAmount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
