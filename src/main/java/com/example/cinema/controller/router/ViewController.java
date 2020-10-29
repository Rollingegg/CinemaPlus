package com.example.cinema.controller.router;

import com.example.cinema.vo.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * @author deng
 * @date 2019/03/11
 */
@Controller
public class ViewController {
    @RequestMapping(value = "/index")
    public String getIndex() {
        return "index";
    }

    @RequestMapping(value = "/signUp")
    public String getSignUp() {
        return "signUp";
    }

    @RequestMapping(value = "/admin/sale/detail")
    public String getAdminMovieBuy(@RequestParam int id) {
        return "adminMovieBuy";
    }

    @RequestMapping(value = "/admin/sale/detail/buy")
    public String getAdminLockSeat(@RequestParam int id) {
        return "adminLockSeat";
    }

    @RequestMapping(value = "/admin/movie/manage")
    public String getAdminMovieManage() {
        return "adminMovieManage";
    }

    @RequestMapping(value = "/admin/session/manage")
    public String getAdminSessionManage() {
        return "adminScheduleManage";
    }

    @RequestMapping(value = "/admin/cinema/manage")
    public String getAdminCinemaManage() {
        return "adminCinemaManage";
    }

    @RequestMapping(value = "/admin/promotion/manage")
    public String getAdminPromotionManage() {
        return "adminPromotionManage";
    }

    @RequestMapping(value = "/admin/member/manage")
    public String getAdminMemberManage(){
        return "adminMemberManage";
    }

    @RequestMapping(value = "/admin/cinema/statistic")
    public String getAdminCinemaStatistic() {
        return "adminCinemaStatistic";
    }

    @RequestMapping(value = "/admin/movieDetail")
    public String getAdminMovieDetail(@RequestParam int id, HttpServletRequest httpServletRequest) {
        HttpSession session=httpServletRequest.getSession();
        UserForm userForm = (UserForm) session.getAttribute("user");
        int level = userForm.getPrivilegeLevel();
        if (level == 0) {
            return "adminMovieDetail";
        } else if (level == 2) {
            return "adminMovieBuy";
        } else {
            return "userNewHome";
        }

    }

    @RequestMapping(value="/admin/account/manage")
    public String getAdminAccountManage() {
        return "adminAccountManage";
    }

    @RequestMapping(value = "/admin/account/detail")
    public String getAccountDetail(@RequestParam int id) {
        return "adminAccountDetail";
    }

    @RequestMapping(value = "/admin/account/coupon/gift")
    public String getAdminCouponGift(@RequestParam int id) {
        return "adminCouponGift";
    }

    @RequestMapping(value = "/user/home")
    public String getUserHome() {
        return "userNewHome";
    }

    @RequestMapping(value = "/user/buy")
    public String getUserBuy() {
        return "userNewBuy";
    }

    @RequestMapping(value = "/user/buy/payment")
    public String getUserPayment(@RequestParam String orderTime) {
        return "userPayment";
    }

    @RequestMapping(value = "/user/consumption")
    public String getUserConsumption() {
        return "userNewConsumption";
    }

    @RequestMapping(value = "/user/movieDetail")
    public String getUserMovieDetail(@RequestParam int id) {
        return "userMovieDetail";
    }

    @RequestMapping(value = "/user/consumptionDetail")
    public String getConsumptionDetail(@RequestParam int id) {
        return "userConsumptionDetail";
    }

    @RequestMapping(value = "/user/movieDetail/buy")
    public String getUserMovieBuy(@RequestParam int id) {
        return "userMovieBuy";
    }

    @RequestMapping(value = "/user/movie")
    public String getUserMovie() {
        return "userNewMovie";
    }

    @RequestMapping(value = "/user/member")
    public String getUserMember() {
        return "userNewMember";
    }
}
