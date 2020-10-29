package com.example.cinema.controller.sales;

import com.example.cinema.bl.sales.TicketService;
import com.example.cinema.vo.OrderForm;
import com.example.cinema.vo.RefundTimeForm;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.TicketForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by liying on 2019/4/16.
 */
@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @PostMapping("/vip/buy")
    public ResponseVO buyTicketByVIPCard(@RequestBody OrderForm orderForm) {
        return ticketService.completeByVIPCard(orderForm);
    }

    @PostMapping("/lockSeat")
    public ResponseVO lockSeat(@RequestBody TicketForm ticketForm) {
        return ticketService.addTicket(ticketForm);
    }

    @PostMapping("/buy")
    public ResponseVO buyTicket(@RequestBody OrderForm orderForm) {
        return ticketService.completeTicket(orderForm);
    }

    @GetMapping("/get/{userId}")
    public ResponseVO getTicketByUserId(@PathVariable int userId) {
        return ticketService.getTicketByUser(userId);
    }

    @GetMapping("/buy/payment/{userId}/{orderTime}")
    public ResponseVO getTicketsInOnePayment(@PathVariable int userId, @PathVariable String orderTime) {
        return ticketService.getTicketsByOrderTime(userId, orderTime);
    }

    @GetMapping("/get/occupiedSeats")
    public ResponseVO getOccupiedSeats(@RequestParam int scheduleId, @RequestParam int userId){
        return ticketService.getByScheduleAndUserId(scheduleId, userId);}

    @GetMapping("/cancel")
    public ResponseVO cancelAllTickets(@RequestParam String idStr) {
        return ticketService.cancelAllTickets(idStr);
    }

    //全额退款
    @RequestMapping(value = "/refund/all/set", method = RequestMethod.POST)
    public ResponseVO setAllRefundTime(@RequestBody RefundTimeForm refundTimeForm){
        return ticketService.setAllRefundTime(refundTimeForm);
    }

    @RequestMapping(value = "/refund/all", method = RequestMethod.GET)
    public ResponseVO getAllRefundTime(){
        return ticketService.getAllRefundTime();
    }

    //部分退款
    @RequestMapping(value = "/refund/part/set", method = RequestMethod.POST)
    public ResponseVO setPartRefundTime(@RequestBody RefundTimeForm refundTimeForm){
        return ticketService.setPartRefundTime(refundTimeForm);
    }

    @RequestMapping(value = "/refund/part", method = RequestMethod.GET)
    public ResponseVO getPartRefundTime(){
        return ticketService.getPartRefundTime();
    }



}

