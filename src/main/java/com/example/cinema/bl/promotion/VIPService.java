package com.example.cinema.bl.promotion;

import com.example.cinema.po.VIPCard;
import com.example.cinema.vo.VIPCardForm;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPInfoForm;


/**
 * Created by liying on 2019/4/14.
 *  Updated by Steve on 2019/6/10.
 */

public interface VIPService {

    ResponseVO addVIPCard(int userId,String type);

    ResponseVO getCardById(int id);

    ResponseVO getVIPInfo(int userId);

    ResponseVO getAllVIPInfo();

    ResponseVO charge(VIPCardForm vipCardForm);

    ResponseVO getCardByUserId(int userId);

    /**
     *  发布会员卡（包括类型、满赠、折扣、发行价）
     * @param vipInfoForm
     * @return
     */
    ResponseVO publishVIPCard(VIPInfoForm vipInfoForm);

    /**
     * 修改充值优惠策略（满赠）
     * @param vipInfoForm
     * @return
     */
    ResponseVO modifyVIPCard(VIPInfoForm vipInfoForm);

}
