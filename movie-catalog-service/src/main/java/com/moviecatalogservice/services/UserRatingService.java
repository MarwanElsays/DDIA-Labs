package com.moviecatalogservice.services;

import com.moviecatalogservice.models.Rating;
import com.moviecatalogservice.models.UserRating;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;

@Service
public class UserRatingService {

    private final RestTemplate restTemplate;

    public UserRatingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "userRatingService", fallbackMethod = "getFallbackUserRatings")
    @TimeLimiter(name = "userRatingService", fallbackMethod = "getFallbackUserRatings")
    public UserRating getUserRating(@PathVariable("userId") String userId) {
            String ratingsUrl = "http://ratings-data-service/ratings/" + userId;
            return Objects.requireNonNull(restTemplate.getForObject(ratingsUrl, UserRating.class));
    }

    public UserRating getFallbackUserRatings(@PathVariable("userId") String userId, Throwable throwable) {
        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setRatings(Collections.singletonList(
                new Rating("0", 0)
        ));
        return userRating;
    }
}