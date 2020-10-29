/**
 * @Author SteveLai
 * @Time 2019/5/13 11:58
 */

package com.example.cinema.vo;

import com.example.cinema.po.PlacingRate;

import java.util.Date;

public class PlacingRateVO {

    private Date date;
    /**
     * 上座率（当日某影片上座率=当日该影片观影人数/∑【当日该电影放映场次*每场所在影片的座位数】）
     */
    private Double placingRate;

    private Integer movieID;
    private String name;
    public PlacingRateVO(PlacingRate pRate){
        this.placingRate=pRate.getPlacingRate();
        this.movieID = pRate.getMovieID();
        this.name = pRate.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPlacingRate() {
        return placingRate;
    }

    public void setPlacingRate(Double placingRate) {
        this.placingRate = placingRate;
    }

    public Integer getMovieID() {
        return movieID;
    }

    public void setMovieID(Integer movieID) {
        this.movieID = movieID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
