package com.example.cinema.vo;

import com.example.cinema.po.Hall;
import com.example.cinema.po.Seat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fjj
 * @date 2019/4/11 3:46 PM
 */
public class HallVO {
    private Integer id;
    private String name;
    /**
     * 在这个二维数组中，0代表空的，1代表有位置，2代表已经被占座
     */
    private int[][] seats;

    public HallVO(Hall hall){
        this.id = hall.getId();
        this.name = hall.getName();
        this.seats = hall.getSeatsLayout();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[][] getSeats() {
        return seats;
    }

    public void setSeats(int[][] seats) {
        this.seats = seats;
    }

    public List<Seat> getSeatList(){
        List<Seat> seatList = new ArrayList<>();
        for(int i=0;i<seats.length;i++){
            for (int j=0;j<seats[i].length;j++){
                if(seats[i][j]==1){
                    Seat seat = new Seat();
                    seat.setRowIndex(i);
                    seat.setColumnIndex(j);
                    seatList.add(seat);
                }
            }
        }
        return seatList;
    }
}
