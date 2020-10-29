package com.example.cinema.blImpl.sales;

import com.example.cinema.bl.sales.ConsumptionService;
import com.example.cinema.testpack.CinemaTest;
import com.example.cinema.vo.ConsumptionVO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ConsumptionServiceImplTest extends CinemaTest {

    @Autowired
    ConsumptionServiceImpl consumptionServiceImpl;

    @Test
    public void getConsumptionByUserId() {
        List<ConsumptionVO> preList = (ArrayList<ConsumptionVO>)consumptionServiceImpl.getConsumptionByUserId(12).getContent();
        consumptionServiceImpl.insertConsumptionItem(12,250,50,3,new ArrayList<>(),0);
        List<ConsumptionVO> list = (ArrayList<ConsumptionVO>)consumptionServiceImpl.getConsumptionByUserId(12).getContent();
        Assert.assertEquals(preList.size()+1,list.size());
    }
}