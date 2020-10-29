package com.example.cinema.blImpl.promotion;

import com.example.cinema.bl.promotion.CouponService;
import com.example.cinema.data.promotion.CouponMapper;
import com.example.cinema.po.Coupon;
import com.example.cinema.vo.CouponForm;
import com.example.cinema.vo.CouponVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by liying on 2019/4/17.
 */
@Service
public class CouponServiceImpl implements CouponService, CouponServiceForBl {

    private static final String REFUND_COUPON_TYPE = "退款补偿";
    private static final String ACTIVITY_COUPON_TYPE = "活动限定";

    @Autowired
    CouponMapper couponMapper;

    @Override
    public ResponseVO getCouponsByUser(int userId) {
        try {
            return ResponseVO.buildSuccess(couponMapper.selectCouponByUser(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO addCoupon(CouponForm couponForm) {
        try {
            Coupon coupon=new Coupon();
            coupon.setName(couponForm.getName());
            coupon.setDescription(couponForm.getDescription());
            coupon.setTargetAmount(couponForm.getTargetAmount());
            coupon.setDiscountAmount(couponForm.getDiscountAmount());
            coupon.setStartTime(couponForm.getStartTime());
            coupon.setEndTime(couponForm.getEndTime());
            couponMapper.insertCoupon(coupon);
            return ResponseVO.buildSuccess(coupon);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO issueCoupon(int couponId, int userId) {
        try {
            couponMapper.insertCouponUser(couponId,userId);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }

    }

    @Override
    public List<CouponVO> getCouponByUser(int userId) {
        List<CouponVO> voList = new ArrayList<>();
        List<Coupon> poList = couponMapper.selectCouponByUser(userId);
        for (Coupon coupon : poList)
            voList.add(coupon.getVO());
        return voList;
    }

    @Override
    public List<CouponVO> getCouponByUserAndAmount(int useId, double amount) {
        List<CouponVO> voList = new ArrayList<>();
        List<Coupon> poList = couponMapper.selectCouponByUserAndAmount(useId, amount);
        for (Coupon coupon : poList)
            voList.add(coupon.getVO());
        return voList;

    }

    @Override
    public ResponseVO getValidCouponList() {
        List<Coupon> list;
        try {
            list = couponMapper.selectAllValidCoupons();
            list.removeIf(coupon -> coupon.getName().startsWith(REFUND_COUPON_TYPE)
                    || coupon.getName().startsWith(ACTIVITY_COUPON_TYPE));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(e.getMessage());
        }
        return ResponseVO.buildSuccess(list);
    }

    @Override
    public CouponVO getCouponById(int couponId) {
        return couponMapper.selectById(couponId).getVO();
    }

    @Override
    public void deleteCouponUser(int couponId, int userId) {
        couponMapper.deleteCouponUser(couponId, userId);
    }

    @Override
    public void insertCouponUser(int couponId, int userId) {
        couponMapper.insertCouponUser(couponId, userId);
    }

    @Override
    public void insertCoupon(Coupon coupon) {
        couponMapper.insertCoupon(coupon);
    }
}
