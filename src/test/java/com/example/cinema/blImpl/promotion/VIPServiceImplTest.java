package com.example.cinema.blImpl.promotion;

import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.po.VIPCard;
import com.example.cinema.testpack.CinemaTest;
import com.example.cinema.vo.VIPCardForm;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class VIPServiceImplTest extends CinemaTest {

    @Autowired
    VIPService vipService;

    @Test
    public void charge() {
        VIPCardForm vipCardForm = new VIPCardForm();
        vipCardForm.setVipId(1);
        vipCardForm.setUserId(15);
        vipCardForm.setAmount(90);
        VIPCard befVipCard = (VIPCard)vipService.getCardByUserId(15).getContent();
        VIPCard aftVipCard = (VIPCard) vipService.charge(vipCardForm).getContent();
        Assert.assertEquals(befVipCard.getBalance()+99,aftVipCard.getBalance(),0.0001);
    }
}