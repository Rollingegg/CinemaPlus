package com.example.cinema.blImpl.promotion;
import com.example.cinema.vo.ActivityVO;

import java.util.List;

/**
 * @author zhihao li
 * @date 2019/5/18 1:20AM
 */
public interface ActivityServiceForBl {
    List<ActivityVO> selectActivitiesByMovie(int movieId);
}