package com.moviecatalogservice.services;

import com.moviecatalogservice.models.CatalogItem;
import com.moviecatalogservice.models.Movie;
import com.moviecatalogservice.models.Rating;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class MovieInfoService {

    private final RestTemplate restTemplate;

    public MovieInfoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "movieInfoService", fallbackMethod = "getFallbackCatalogItem")
    @TimeLimiter(name = "movieInfoService", fallbackMethod = "getFallbackCatalogItem")
    public CatalogItem getCatalogItem(Rating rating) {
            String movieDetailsUrl = "http://movie-info-service/movies/" + rating.getMovieId();
            Movie movie = this.restTemplate.getForObject(movieDetailsUrl, Movie.class);
            return CatalogItem.builder()
                    .name(movie.getName())
                    .description(movie.getDescription())
                    .rating(rating.getRating())
                    .build();
    }

    public CatalogItem getFallbackCatalogItem(Rating rating, Throwable throwable) {
        return new CatalogItem("Movie name not found", "", rating.getRating());
    }
}