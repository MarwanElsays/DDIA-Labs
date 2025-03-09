package com.example.ratingsservice.models;

import java.util.List;

public class UserRatingModel {
    private List<RatingModel> ratings;

    public UserRatingModel() {
    }

    public UserRatingModel(List<RatingModel> ratings) {
        this.ratings = ratings;
    }

    public List<RatingModel> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingModel> ratings) {
        this.ratings = ratings;
    }
}
