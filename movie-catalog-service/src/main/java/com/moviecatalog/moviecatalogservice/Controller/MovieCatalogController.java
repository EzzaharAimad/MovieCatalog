package com.moviecatalog.moviecatalogservice.Controller;

import com.moviecatalog.moviecatalogservice.Model.CatalogItem;
import com.moviecatalog.moviecatalogservice.Model.Movie;
import com.moviecatalog.moviecatalogservice.Model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId")  String userId){

        //get all rated movie IDs
        List<Rating> ratings =Arrays.asList(new Rating("1234",3),new Rating("5678",5));

        return ratings.stream().map(rating ->{
                Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+ rating.getMovieId(), Movie.class);
                return new CatalogItem(movie.getName(), "test", rating.getRating());
        }).collect(Collectors.toList());

        //for each movie ID call movie info service and get details
        //Put em all together

    }
}
