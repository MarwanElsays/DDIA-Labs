package com.example.ratingsservice.service;

import com.example.ratingsservice.Entity.Rating;
import com.example.ratingsservice.Repository.RatingRepository;
import com.example.ratingsservice.models.RatingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public List<RatingModel> getRatingsOfUser(String userId) {
        Optional<List<Rating>> ratingList =  ratingRepository.findAllRatingsByUserId(userId);
        return ratingList.map(ratings -> ratings.stream().map(this::ratingToRatingModel)
                .collect(Collectors.toList()))
                .orElse(null);
    }

    private RatingModel ratingToRatingModel(Rating rating) {
        return RatingModel.builder()
                .movieId(rating.getRatingId().getMovieId())
                .rating(rating.getRatingValue())
                .build();
    }
}
