package com.example.cinema.vo;

public class UserDetailVO {
    private Integer userId;

    private String username;

    private Integer privilegeLevel;

    private Double totalConsumption;

    private Boolean validVIP;

    private Double vipBalance;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getPrivilegeLevel() {
        return privilegeLevel;
    }

    public void setPrivilegeLevel(Integer privilegeLevel) {
        this.privilegeLevel = privilegeLevel;
    }

    public Double getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(Double totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public Boolean getValidVIP() {
        return validVIP;
    }

    public void setValidVIP(Boolean VIP) {
        validVIP = VIP;
    }

    public Double getVipBalance() {
        return vipBalance;
    }

    public void setVipBalance(Double vipBalance) {
        this.vipBalance = vipBalance;
    }
}
