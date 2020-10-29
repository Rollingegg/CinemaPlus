package com.example.cinema.blImpl.statistics;

import com.example.cinema.bl.statistics.MovieLikeService;
import com.example.cinema.testpack.CinemaTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class MovieLikeServiceImplTest extends CinemaTest {

    @Autowired
    MovieLikeService movieLikeService;

    @Test
    public void getCountOfLikes() {
        int befNum = (int) movieLikeService.getCountOfLikes(10).getContent();
        movieLikeService.likeMovie(15,10);
        int aftNum = (int) movieLikeService.getCountOfLikes(10).getContent();
        Assert.assertEquals(befNum+1,aftNum);
    }
}