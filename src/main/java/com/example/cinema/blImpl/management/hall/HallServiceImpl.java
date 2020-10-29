package com.example.cinema.blImpl.management.hall;

import com.example.cinema.bl.management.HallService;
import com.example.cinema.data.management.HallMapper;
import com.example.cinema.po.Hall;
import com.example.cinema.po.Seat;
import com.example.cinema.vo.HallForm;
import com.example.cinema.vo.HallVO;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fjj
 * @date 2019/4/12 2:01 PM
 */
@Service
public class HallServiceImpl implements HallService, HallServiceForBl {
    @Autowired
    private HallMapper hallMapper;

    @Override
    public ResponseVO searchAllHall() {
        try {
            return ResponseVO.buildSuccess(hallList2HallVOList(hallMapper.selectAllHall()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public Hall getHallById(int id) {
        try {
            return hallMapper.selectHallById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public ResponseVO addHall(HallForm hallForm) {
        Hall hall = new Hall();
        hall.setName(hallForm.getName());
        hallMapper.insertHall(hall);//hall获取到了id
        List<Seat> seatList = rearrangeSeats(hallForm.getSeats());
        hallMapper.insertHallSeats(hall.getId(), seatList);
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO updateHall(HallForm hallForm) {
        Hall hall = new Hall();
        hall.setId(hallForm.getHallId());
        hall.setName(hallForm.getName());
        List<Seat> seatList = rearrangeSeats(hallForm.getSeats());
        hallMapper.updateHall(hall);
        hallMapper.deleteHallSeats(hallForm.getHallId());
        hallMapper.insertHallSeats(hallForm.getHallId(), seatList);
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO deleteHall(int hallId) {
        hallMapper.deleteHall(hallId);
        hallMapper.deleteHallSeats(hallId);
        return ResponseVO.buildSuccess();
    }

//    private boolean isInUse(int hallId){
//
//    }

    //使排和列都从0开始
    private List<Seat> rearrangeSeats(List<Seat> seats){
        List<Seat> newSeats = new ArrayList<>();
        int minRow = 20;
        int minColumn = 20;
        for(int i=0;i<seats.size();i++){
            int row = seats.get(i).getRowIndex();
            int column = seats.get(i).getColumnIndex();
            if(row < minRow){
                minRow = row;
            }
            if(column < minColumn){
                minColumn = column;
            }
        }
        for(int i=0;i<seats.size();i++){
            Seat newSeat = new Seat();
            newSeat.setRowIndex(seats.get(i).getRowIndex()-minRow);
            newSeat.setColumnIndex(seats.get(i).getColumnIndex()-minColumn);
            newSeats.add(newSeat);
        }
        return newSeats;
    }

    private List<HallVO> hallList2HallVOList(List<Hall> hallList){
        List<HallVO> hallVOList = new ArrayList<>();
        for(Hall hall : hallList){
            hallVOList.add(new HallVO(hall));
        }
        return hallVOList;
    }
}
