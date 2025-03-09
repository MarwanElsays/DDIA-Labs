package com.example.ratingsservice.resources;

import com.example.ratingsservice.models.RatingModel;
import com.example.ratingsservice.models.UserRatingModel;
import com.example.ratingsservice.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingsResource {

    @Autowired
    private RatingService ratingService;

    @RequestMapping("/{userId}")
    public UserRatingModel getRatingsOfUser(@PathVariable String userId) {
        List<RatingModel> ratingsList = ratingService.getRatingsOfUser(userId);
        return UserRatingModel.builder().ratings(ratingsList).userId(userId).build();
    }
}
