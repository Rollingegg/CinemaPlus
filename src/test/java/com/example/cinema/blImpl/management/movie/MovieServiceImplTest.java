package com.example.cinema.blImpl.management.movie;

import com.example.cinema.bl.management.MovieService;
import com.example.cinema.testpack.CinemaTest;
import com.example.cinema.vo.MovieBatchOffForm;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MovieServiceImplTest extends CinemaTest {

    @Autowired
    MovieService movieService;

    @Test
    public void pullOfBatchOfMovie() {
        MovieBatchOffForm movieBatchOffForm = new MovieBatchOffForm();
        List<Integer> movieIds = new ArrayList<>();
        movieIds.add(10);
        movieBatchOffForm.setMovieIdList(movieIds);
        Assert.assertEquals("有电影后续仍有排片或已有观众购票且未使用",movieService.pullOfBatchOfMovie(movieBatchOffForm).getMessage());
    }
}