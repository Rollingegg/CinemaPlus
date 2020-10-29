package com.example.cinema.bl.sales;

import com.example.cinema.vo.ResponseVO;

public interface ConsumptionService {

    /**
     * 获取用户的消费记录
     * 缩略模式的列表
     * @param userId
     * @return
     */
    ResponseVO getConsumptionByUserId(int userId);

    /**
     * 获得一条详细的消费记录
     * @param consumptionId
     * @return
     */
    ResponseVO getConsumptionDetails(int consumptionId);
}
