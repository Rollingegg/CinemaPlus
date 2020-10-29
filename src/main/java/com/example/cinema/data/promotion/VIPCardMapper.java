package com.example.cinema.data.promotion;

import com.example.cinema.po.VIPCard;
import com.example.cinema.po.VIPCardInfo;
import com.example.cinema.vo.VIPInfoForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liying on 2019/4/14.
 * Updated by Steve on 2019/6/10.
 */
@Mapper
public interface VIPCardMapper {

    /**
     * 插入一条VIPCard记录
     * @param vipCard
     * @return 插入数据后改变的记录条数
     */
    int insertOneCard(VIPCard vipCard);

    VIPCard selectCardById(int id);

    /**
     * 根据用户ID更新余额为balance
     * @param userId
     * @param balance
     */
    void updateCardBalance(@Param("userId") int userId,@Param("balance") double balance);

    /**
     * 根据用户ID选取会员卡
     * @param userId
     * @return
     */
    VIPCard selectCardByUserId(int userId);

    /**
     * 根据用户ID选取会员卡信息
     * @param userId
     * @return
     */
    VIPCardInfo selectCardInfoByUserId(@Param("userId")int userId);
    VIPCardInfo selectCardInfoById(@Param("id") int id);

    /**
     * 展示所有种类的会员卡
     * @return
     */
    List<VIPCardInfo> selectAllCardInfo();

    VIPCardInfo selectCardInfoByType(@Param("type") String type);
    //TODO 根据会员卡type返回cardId
    int queryCardIdByType(@Param("type")String type);
    //TODO 发布新会员卡
    int insertOneCardInfo(VIPCardInfo vipCardInfo);

    void updateCardInfo(VIPInfoForm vipInfoForm);

    //TODO 更新充值优惠
    void updateCardInfoWithAmount(@Param("type") String type,@Param("targetAmount")int targetAmount,@Param("bonusAmount")int bonusAmount);
    //TODO 更新会员卡买票折扣
    void updateCardDiscount(@Param("discount")double discount);

    void updateCardPrice(@Param("price")double price);
    //TODO 根据历史充值总额更新会员卡种类
    void updateCardType(@Param("type")String type);

    double getDiscountByCardType(@Param("type")String cardType);
}
