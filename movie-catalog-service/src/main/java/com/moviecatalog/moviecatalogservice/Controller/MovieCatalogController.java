package com.moviecatalog.moviecatalogservice.Controller;

import com.moviecatalog.moviecatalogservice.Model.CatalogItem;
import com.moviecatalog.moviecatalogservice.Model.Movie;
import com.moviecatalog.moviecatalogservice.Model.Rating;
import com.moviecatalog.moviecatalogservice.Model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogController {

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId")  String userId){

        //get all rated movie IDs
        UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingdata/users/"+ userId, UserRating.class);

        return ratings.getUserRating().stream().map(rating ->{
            //for each movie ID call movie info service and get details
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+ rating.getMovieId(), Movie.class);

            //Put em all together
            return new CatalogItem(movie.getName(), "test", rating.getRating());
        }).collect(Collectors.toList());
    }
}

/*
            Movie movie = webClientBuilder.build()
                    .get()//means that you're getting, the whole instruction is GET method
                    .uri("http://localhost:8082/movies/"+ rating.getMovieId())//the URL we want to access
                    .retrieve()//go do the fetch
                    .bodyToMono(Movie.class)//Whatever body you get back, convert it to an instance of the class (MONO is a reactive an object back in the future, it's a promise of getting what you want (asynchronous))
                    .block();//blocking execution till MONO is fulfilled, the block makes synchronus
                    //which means WebClient is more modern and it supports both synchronous and asynchronous
            */

