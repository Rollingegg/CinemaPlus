package com.example.cinema.data.management;

import com.example.cinema.po.Hall;
import com.example.cinema.po.Seat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author fjj
 * @date 2019/4/11 3:46 PM
 */
@Mapper
public interface HallMapper {
    /**
     * 查询所有影厅信息
     * @return
     */
    List<Hall> selectAllHall();

    /**
     * 根据id查询影厅
     * @return
     */
    Hall selectHallById(@Param("hallId") int hallId);

    /**
     * 增添一个影院
     * @param hall
     */
    void insertHall(Hall hall);

    void insertHallSeats(@Param("hallId") int hallId,@Param("seats") List<Seat> seats);

    void updateHall(Hall hall);

    void deleteHallSeats(int hallId);

    void deleteHall(int hallId);


}
