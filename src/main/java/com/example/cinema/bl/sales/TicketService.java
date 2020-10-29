package com.example.cinema.bl.sales;

import com.example.cinema.vo.OrderForm;
import com.example.cinema.vo.RefundTimeForm;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.TicketForm;

import java.sql.Timestamp;
import java.util.List;


/**
 * Created by liying on 2019/4/16.
 */
public interface TicketService {
    /**
     *
     *
     * @param ticketForm
     * @return
     */
    ResponseVO addTicket(TicketForm ticketForm);

    /**
     * 完成购票【不使用会员卡】流程包括校验优惠券和根据优惠活动赠送优惠券
     * @return
     */
    ResponseVO completeTicket(OrderForm orderForm);

    /**
     * 获得该场次的被锁座位和场次信息
     *
     * @param scheduleId
     * @param userId
     * @return
     */
    ResponseVO getByScheduleAndUserId(int scheduleId, int userId);

    /**
     * 获得用户买过的票
     *
     * @param userId
     * @return
     */
    ResponseVO getTicketByUser(int userId);

    /**
     * 完成购票【使用会员卡】流程包括会员卡扣费、校验优惠券和根据优惠活动赠送优惠券
     *
     * @return
     */
    ResponseVO completeByVIPCard(OrderForm orderForm);

    /**
     * 取消锁座（只有状态是"锁定中"的可以取消）
     *
     */
    ResponseVO cancelAllTickets(String ticketIdStr);


    ResponseVO getTicketsByOrderTime(int userId, String orderTime);


    ResponseVO setAllRefundTime(RefundTimeForm refundTimeForm);

    ResponseVO getAllRefundTime();

    ResponseVO setPartRefundTime(RefundTimeForm refundTimeForm);

    ResponseVO getPartRefundTime();

}
