package com.moviecatalog.ratingdataservice.Controller;

import com.moviecatalog.ratingdataservice.Model.Rating;
import com.moviecatalog.ratingdataservice.Model.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingdata")
public class RatingDataController {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return new Rating(movieId, 4);
    }

    @RequestMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId){
        List<Rating> ratings = Arrays.asList(new Rating("1234",3),new Rating("5678",5));
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return userRating;

    }
}

