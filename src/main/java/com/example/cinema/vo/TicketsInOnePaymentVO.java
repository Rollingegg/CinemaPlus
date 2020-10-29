package com.example.cinema.vo;

import com.example.cinema.po.Movie;
import com.example.cinema.po.ScheduleItem;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by jiayi chen on 2019/6/2.
 */
public class TicketsInOnePaymentVO {
    /**
     * 电影票id
     */
    private int id;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 排片id
     */
    private ScheduleItem schedule;

    /**
     * 所有座位
     */
    private String seatsStr;

    /**
     * 所有的票的id
     */
    private List<Integer> ticketIds;

    /**
     * 订单状态
     */
    private String state;

    /**
     * 下单时间
     */
    private Timestamp time;

    /**
     * 电影信息
     */
    private Movie movie;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ScheduleItem getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleItem schedule) {
        this.schedule = schedule;
    }

    public String getSeatsStr() {
        return seatsStr;
    }

    public void setSeatsStr(String seatsStr) {
        this.seatsStr = seatsStr;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Integer> getTicketIds() {
        return ticketIds;
    }

    public void setTicketIds(List<Integer> ticketIds) {
        this.ticketIds = ticketIds;
    }
}
