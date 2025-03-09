package com.example.ratingsservice.service;

import com.example.ratingsservice.Entity.Rating;
import com.example.ratingsservice.Repository.RatingRepository;
import com.example.ratingsservice.models.RatingModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    public List<RatingModel> getRatingsOfUser(String userId) {

        List<Rating> ratingList =  ratingRepository.findByUserId(userId);

        return ratingList.stream().map(rating1 -> new RatingModel(rating1.getMovieId(), rating1.getRating()))
                .collect(Collectors.toList());
    }
}
