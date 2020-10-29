package com.example.cinema.data.sales;

import com.example.cinema.po.ConsumptionItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ConsumptionMapper {

    void insertConsumption(ConsumptionItem consumptionItem);

    void insertConsumptionAndTicket(@Param("consumptionId") int consumptionId,@Param("ticketId") List<Integer> ticketId);

    List<ConsumptionItem> selectConsumptionByUserId(int userId);

    ConsumptionItem selectConsumptionById(int consumptionId);
}
