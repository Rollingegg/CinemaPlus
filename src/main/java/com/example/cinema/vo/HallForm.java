package com.example.cinema.vo;

import com.example.cinema.po.Seat;

import java.util.List;

public class HallForm {

    private int hallId;
    private String name;
    private List<Seat> seats;

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
