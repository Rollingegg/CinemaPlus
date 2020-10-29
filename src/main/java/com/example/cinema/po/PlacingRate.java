/**
 * @Author Administrator
 * @Time 2019/5/16 21:32
 */

package com.example.cinema.po;

public class PlacingRate {
    /**
     * 上座率（当日某影片上座率=当日该影片观影人数/∑【当日该电影放映场次*每场所在影片的座位数】）
     */
    private Double placingRate;

    /**
     * 当日该影片观影人数
     */
    private Integer attendance;

    /**
     * 当日该电影所有放映场次所在影厅座位数之和
     */
    private Integer seatNums;

    private Integer movieId;
    private String name;

    public Double getPlacingRate() {
        return placingRate;
    }

    public void setPlacingRate(Double placingRate) {
        this.placingRate = placingRate;
    }

    public Integer getMovieID() {
        return movieId;
    }

    public void setMovieID(Integer movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAttendance() {
        return attendance;
    }

    public void setAttendance(Integer attendance) {
        this.attendance = attendance;
    }

    public Integer getSeatNums() {
        return seatNums;
    }

    public void setSeatNums(Integer seatNums) {
        this.seatNums = seatNums;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }
}
