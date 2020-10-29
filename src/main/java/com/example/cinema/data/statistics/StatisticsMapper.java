package com.example.cinema.data.statistics;

import com.example.cinema.po.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author fjj
 * @date 2019/4/16 1:43 PM
 */
@Mapper
public interface StatisticsMapper {
    /**
     * 查询date日期每部电影的排片次数
     *
     * @param date 起始日期
     * @param nextDate 结束日期
     * @return
     */
    List<MovieScheduleTime> selectMovieScheduleTimes(@Param("date") Date date, @Param("nextDate") Date nextDate);

    /**
     * 查询所有电影的总票房（包括已经下架的，降序排列）
     *
     * @return
     */
    List<MovieTotalBoxOffice> selectMovieTotalBoxOffice();

    /**
     * 查询所有电影的从date到nextDate之间的票房（包括已经下架的，降序排列）
     *
     * @param date
     * @param nextDate
     * @return
     */
    List<MovieBoxOffice> selectMovieBoxOffice(@Param("date") Date date, @Param("nextDate") Date nextDate);

    /**
     * 查询某天每个客户的购票金额
     *
     * @param date
     * @param nextDate
     * @return
     */
    List<AudiencePrice> selectAudiencePrice(@Param("date") Date date, @Param("nextDate") Date nextDate);

    /**
     * 查询所有电影的从date到nextDate之间的上座率（包括已经下架的，降序排列）
     * @param date
     * @param nextDate
     * @return
     */
    List<PlacingRate> selectPlacingRate(@Param("date") Date date, @Param("nextDate") Date nextDate);
}
