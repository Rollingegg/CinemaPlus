package com.example.cinema.vo;

import com.example.cinema.po.Movie;
import com.example.cinema.po.ScheduleItem;
import com.example.cinema.po.Ticket;

import java.sql.Timestamp;

/**
 * Created by liying on 2019/4/16.
 */
public class TicketWithScheduleVO {

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
     * 列号
     */
    private int columnIndex;
    /**
     * 排号
     */
    private int rowIndex;

    /**
     * 订单状态
     */
    private String state;

    /**
     * 下单时间
     */
    private Timestamp time;

    /**
     * 下单时间
     */
    private Movie movie;


    public TicketWithScheduleVO() {
        this(new Ticket(), new ScheduleItem());
    }

    public TicketWithScheduleVO(Ticket ticket, ScheduleItem schedule) {
        setId(ticket.getId());
        setUserId(ticket.getUserId());
        setSchedule(schedule);
        setColumnIndex(ticket.getColumnIndex());
        setRowIndex(ticket.getRowIndex());
        String stateString;
        switch (ticket.getState()) {
            case 1:
                stateString = "已完成";
                break;
            case 2:
                stateString = "已失效";
                break;
            default:
                stateString = "未完成";
        }
        setState(stateString);
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

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

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
