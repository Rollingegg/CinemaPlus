package com.example.cinema.blImpl.promotion;

import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.blImpl.sales.ConsumptionServiceForBL;
import com.example.cinema.data.promotion.VIPCardMapper;
import com.example.cinema.po.VIPCardInfo;
import com.example.cinema.vo.*;
import com.example.cinema.po.VIPCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by liying on 2019/4/14.
 * Updated by Steve on 2019/6/10.
 */
@Service
public class VIPServiceImpl implements VIPService, VIPServiceForBl {
    private final String SOLD_CONFLICT_ERROR_MESSAGE="会员卡已出售，无法删除或修改";
    @Autowired
    VIPCardMapper vipCardMapper;
    @Autowired
    ConsumptionServiceForBL consumptionService;

    @Override
    public ResponseVO addVIPCard(int userId,String type) {
        VIPCard vipCard = new VIPCard();
        vipCard.setUserId(userId);
        // 与特定种类的会员卡绑定
        vipCard.setType(type);

        vipCard.setBalance(0);
        //插入消费记录
        VIPCardInfo vipCardInfo = vipCardMapper.selectCardInfoByType(type);
        double price = vipCardInfo.getPrice();
        //注意：payMethod = 2代表购买会员卡
        consumptionService.insertConsumptionItem(userId, price, 0, 2, new ArrayList<>(), 0);

        try {
            vipCardMapper.insertOneCard(vipCard);
            return ResponseVO.buildSuccess(vipCardMapper.selectCardByUserId(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getCardById(int id) {
        try {
            return ResponseVO.buildSuccess(vipCardMapper.selectCardById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getVIPInfo(int userId) {
        try {
            VIPInfoVO vipInfoVO = vipCardMapper.selectCardInfoByUserId(userId).getVO();
            return ResponseVO.buildSuccess(vipInfoVO);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getAllVIPInfo() {
        return ResponseVO.buildSuccess(CardInfo2CardInfoVO(vipCardMapper.selectAllCardInfo()));
    }

    @Override
    public ResponseVO charge(VIPCardForm vipCardForm) {

        VIPCard vipCard = vipCardMapper.selectCardByUserId(vipCardForm.getUserId());
        if (vipCard == null) {
            return ResponseVO.buildFailure("会员卡不存在");
        }
        VIPCardInfo vipCardInfo = vipCardMapper.selectCardInfoByUserId(vipCardForm.getUserId());

        //记录充值会员卡的消费记录
        double pay = vipCardForm.getAmount();
        double balanceAdd = vipCardInfo.calculate(vipCardForm.getAmount());
        double discountAmount = balanceAdd - pay;
        //注意，payMethod=3代表这是一条充值会员卡的记录
        consumptionService.insertConsumptionItem(vipCardForm.getUserId(), balanceAdd, discountAmount, 3, new ArrayList<>(), 0);

        vipCard.setBalance(vipCard.getBalance() + balanceAdd);
        try {
            vipCardMapper.updateCardBalance(vipCardForm.getUserId(), vipCard.getBalance());
            return ResponseVO.buildSuccess(vipCard);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getCardByUserId(int userId) {
        try {
            VIPCard vipCard = vipCardMapper.selectCardByUserId(userId);
            if(vipCard==null){
                return ResponseVO.buildFailure("用户卡不存在");
            }
            return ResponseVO.buildSuccess(vipCard);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public VIPCard getVIPCardByUserId(int userId) {
        return vipCardMapper.selectCardByUserId(userId);
    }

    /**
     * 支付后的余额更新
     * @param userId
     * @param amount 支付金额
     * @return 是否支付成功
     */
    @Override
    public boolean updateVIPCardBalance(int userId, double amount) {
        VIPCard card = vipCardMapper.selectCardByUserId(userId);
        double balance = card.getBalance();
        if(balance < amount){
            return false;
        }
        else {
            vipCardMapper.updateCardBalance(card.getUserId(), balance - amount);
            return true;
        }
    }

    @Override
    public double getBalance(int userId) {
        System.out.println(userId);
        return vipCardMapper.selectCardByUserId(userId).getBalance();
    }

    @Override
    public ResponseVO publishVIPCard(VIPInfoForm vipInfoForm) {
        VIPCardInfo vipCardInfo=new VIPCardInfo();
        vipCardInfo.setType(vipInfoForm.getType());
        vipCardInfo.setTargetAmount(vipInfoForm.getTargetAmount());
        vipCardInfo.setBonusAmount(vipInfoForm.getBonusAmount());
        vipCardInfo.setDiscount(vipInfoForm.getDiscount());
        vipCardInfo.setPrice(vipInfoForm.getPrice());
        try{
            int id = vipCardMapper.insertOneCardInfo(vipCardInfo);
            return ResponseVO.buildSuccess(vipCardMapper.selectCardInfoById(id));
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO modifyVIPCard(VIPInfoForm vipInfoForm) {
        try {
            // 在修改时如果会员卡已出售，那么该会员卡将不可更改？
            vipCardMapper.updateCardInfoWithAmount(vipInfoForm.getType(),vipInfoForm.getTargetAmount(),vipInfoForm.getBonusAmount());
            System.out.println("OK!!");
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public double getVipDiscountByUserId(int id) {
        VIPInfoVO vipInfoVO = vipCardMapper.selectCardInfoByUserId(id).getVO();
        return vipInfoVO.getDiscount();
    }

    /**
     * 检查该会员卡是否已出售
     */
    public boolean isMemberCardSold(String type){
        return true;
    }

    private List<VIPInfoVO> CardInfo2CardInfoVO(List<VIPCardInfo> list){
        List<VIPInfoVO> infoVOList = new ArrayList<>();
        for (VIPCardInfo cardInfo:list){
            VIPInfoVO infoVO = new VIPInfoVO(cardInfo);
            infoVOList.add(infoVO);
        }
        return infoVOList;
    }
}
