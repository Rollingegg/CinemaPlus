package com.example.cinema.po;

import java.util.List;

/**
 * @author fjj
 * @date 2019/4/28 5:09 PM
 */
public class Hall {

    private Integer id;
    private String name;
    private List<Seat> seats;
    //一号厅5*10，二号厅8*12

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

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public int[][] getSeatsLayout(){
        int maxRow = 0;
        int maxColumn = 0;
        for(Seat seat: seats){
            if(seat.getRowIndex()>maxRow){
                maxRow = seat.getRowIndex();
            }
            if(seat.getColumnIndex()>maxColumn){
                maxColumn = seat.getColumnIndex();
            }
        }
        int[][] seatsLayout = new int[maxRow+1][maxColumn+1];//已经默认全设为0
        for (Seat seat: seats){
            //将有座位的地方设为1
            seatsLayout[seat.getRowIndex()][seat.getColumnIndex()] = 1;
        }
        return seatsLayout;
    }
}
