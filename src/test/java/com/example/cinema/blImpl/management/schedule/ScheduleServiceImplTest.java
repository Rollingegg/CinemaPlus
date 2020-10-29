package com.example.cinema.blImpl.management.schedule;

import com.example.cinema.bl.management.ScheduleService;
import com.example.cinema.testpack.CinemaTest;
import com.example.cinema.vo.ScheduleForm;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class ScheduleServiceImplTest extends CinemaTest {

    @Autowired
    ScheduleService scheduleService;

    @Test
    public void updateSchedule() {
//        70, 1, 10, '2019-06-14 19:30:00', '2019-06-14 21:30:00', 40
        ScheduleForm scheduleForm = new ScheduleForm();
        scheduleForm.setId(71);
        scheduleForm.setHallId(1);
        scheduleForm.setMovieId(10);
        scheduleForm.setStartTime(toDate("2019-06-23 19:30:00"));
        scheduleForm.setEndTime(toDate("2019-06-23 21:30:00"));
        scheduleForm.setFare(50.0);
        scheduleService.addSchedule(scheduleForm);
        scheduleForm.setStartTime(toDate("2019-06-23 19:20:00"));
        Assert.assertEquals("有排片信息已对观众可见，无法删除或修改",scheduleService.updateSchedule(scheduleForm).getMessage());
    }

    public Date toDate(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }
}