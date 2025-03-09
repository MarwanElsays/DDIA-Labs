package com.example.ratingsservice.service;

import com.example.ratingsservice.Entity.Rating;
import com.example.ratingsservice.Repository.RatingRepository;
import com.example.ratingsservice.models.RatingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public List<RatingModel> getRatingsOfUser(String userId) {
        Optional<List<Rating>> ratingList = ratingRepository.findAllRatingsByUserId(userId);
        return ratingList.orElse(Collections.emptyList())
                .stream()
                .map(this::ratingToRatingModel)
                .collect(Collectors.toList());
    }

    public List<String> getTop10Ratings() {
        Optional<List<Object[]>> result = ratingRepository.findTop10ByOrderByRatingValueDesc();

        return result.orElse(Collections.emptyList())
                .stream()
                .map(row -> (String) row[0])
                .collect(Collectors.toList());
    }

    private RatingModel ratingToRatingModel(Rating rating) {
        return RatingModel.builder()
                .movieId(rating.getRatingId().getMovieId())
                .rating(rating.getRatingValue())
                .build();
    }
}
