package com.example.cinema.blImpl.sales;

import com.example.cinema.bl.sales.ConsumptionService;
import com.example.cinema.blImpl.management.schedule.MovieServiceForBl;
import com.example.cinema.blImpl.management.schedule.ScheduleServiceForBl;
import com.example.cinema.blImpl.promotion.CouponServiceForBl;
import com.example.cinema.data.sales.ConsumptionMapper;
import com.example.cinema.data.sales.TicketMapper;
import com.example.cinema.po.ConsumptionItem;
import com.example.cinema.po.ScheduleItem;
import com.example.cinema.po.Ticket;
import com.example.cinema.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsumptionServiceImpl implements ConsumptionService, ConsumptionServiceForBL {

    @Autowired
    ConsumptionMapper consumptionMapper;
    @Autowired
    MovieServiceForBl movieServiceForBl;
    @Autowired
    TicketServiceForBL ticketService;
    @Autowired
    ScheduleServiceForBl scheduleService;
    @Autowired
    CouponServiceForBl couponService;

    @Override
    public double totalConsumption(int userId) {
        double totalPay = 0.0;
        List<ConsumptionItem> consumptionItems = consumptionMapper.selectConsumptionByUserId(userId);
        for(ConsumptionItem item: consumptionItems){
            double total = item.getTotal();
            double discountAmount = item.getDiscountAmount();
            totalPay += total-discountAmount;
        }
        return totalPay;
    }

    @Override
    public double totalTicketPrice(int userId) {
        double totalPrice = 0.0;
        List<ConsumptionItem> consumptionItems = consumptionMapper.selectConsumptionByUserId(userId);
        for(ConsumptionItem item: consumptionItems){
            double total = item.getTotal();
            totalPrice += total;
        }
        return totalPrice;
    }

    @Override
    public void insertConsumptionItem(int userId, double total, double discountAmount, int payMethod, List<Integer> ticketId, int couponId) {

        ConsumptionItem consumptionItem = new ConsumptionItem();
        consumptionItem.setUserId(userId);
        consumptionItem.setTotal(total);
        consumptionItem.setDiscountAmount(discountAmount);
        consumptionItem.setPayMethod(payMethod);
        consumptionItem.setCouponId(couponId);
        consumptionMapper.insertConsumption(consumptionItem);
        int consumptionId = consumptionItem.getId();
        if(ticketId.size()!=0){
            consumptionMapper.insertConsumptionAndTicket(consumptionId, ticketId);
        }

    }

    @Override
    public ResponseVO getConsumptionByUserId(int userId) {
        List<ConsumptionItem> consumptionItems = consumptionMapper.selectConsumptionByUserId(userId);
        List<ConsumptionVO> consumptionVOList = new ArrayList<>();
        for(ConsumptionItem item: consumptionItems){
            ConsumptionVO consumptionVO = new ConsumptionVO();
            consumptionVO.setId(item.getId());
            consumptionVO.setDiscountAmount(item.getDiscountAmount());
            consumptionVO.setTotal(item.getTotal());
            consumptionVO.setPay(item.getTotal()-item.getDiscountAmount());
            String movieName = "";
            List<Integer> ticketIdList = item.getTicketId();
            if(ticketIdList.size()!=0){
                Ticket ticket = ticketService.getTicketById(ticketIdList.get(0));
                ScheduleItem scheduleItem = scheduleService.getScheduleItemById(ticket.getScheduleId());
                movieName = movieServiceForBl.getMovieById(scheduleItem.getMovieId()).getName();
            }
            int payMethod = item.getPayMethod();
            if(payMethod==2){
                movieName = "购买会员卡";
            }
            else if(payMethod==3){
                movieName = "会员卡充值";
            }
            consumptionVO.setMovieName(movieName);
            consumptionVO.setTicketNum(item.getTicketId().size());
            consumptionVO.setPayMethod(payMethod==0? "会员卡":"银行卡");
            consumptionVO.setTime(item.getTime());
            consumptionVOList.add(consumptionVO);
        }
        return ResponseVO.buildSuccess(consumptionVOList);
    }

    @Override
    public ResponseVO getConsumptionDetails(int consumptionId) {
        ConsumptionItem consumptionItem = consumptionMapper.selectConsumptionById(consumptionId);
        ConsumptionDetailsVO consumptionDetailsVO = new ConsumptionDetailsVO();
        consumptionDetailsVO.setId(consumptionItem.getId());
        consumptionDetailsVO.setUserId(consumptionItem.getUserId());
        consumptionDetailsVO.setTotal(consumptionItem.getTotal());
        consumptionDetailsVO.setDiscountAmount(consumptionItem.getDiscountAmount());
        consumptionDetailsVO.setPay(consumptionItem.getTotal()-consumptionItem.getDiscountAmount());
        consumptionDetailsVO.setPayMethod(consumptionItem.getPayMethod()==0? "会员卡":"银行卡");
        List<TicketWithScheduleVO> ticketVOList = new ArrayList<>();
        for(int i: consumptionItem.getTicketId()){
            Ticket ticket = ticketService.getTicketById(i);
            ticketVOList.add(new TicketWithScheduleVO(ticket, scheduleService.getScheduleItemById(ticket.getScheduleId())));
        }
        consumptionDetailsVO.setTicketList(ticketVOList);
        int couponId = consumptionItem.getCouponId();
        if(couponId!=0){
            CouponVO couponVO = couponService.getCouponById(couponId);
            consumptionDetailsVO.setCouponVO(couponVO);
        }
        consumptionDetailsVO.setTime(consumptionItem.getTime());
        return ResponseVO.buildSuccess(consumptionDetailsVO);
    }
}
