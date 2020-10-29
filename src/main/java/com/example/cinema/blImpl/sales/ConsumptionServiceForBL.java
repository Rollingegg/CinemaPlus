package com.example.cinema.blImpl.sales;


import java.util.List;

public interface ConsumptionServiceForBL {

    /**
     * 看用户总共花了多少钱
     */
    double totalConsumption(int userId);

    /**
     * 看用户买的的票的原价为多少钱
     * @param userId
     * @return
     */
    double totalTicketPrice(int userId);

    /**
     * 增加一条消费/退款记录，
     * 如果是退款，姑且将total-discountAmount置为负数，couponId可以是0
     * @param userId
     * @param total
     * @param discountAmount
     * @param payMethod 1为购票 2为购卡 3为充卡
     * @param ticketId
     * @param couponId
     */
    void insertConsumptionItem(int userId, double total, double discountAmount,
                               int payMethod, List<Integer> ticketId, int couponId);

}
