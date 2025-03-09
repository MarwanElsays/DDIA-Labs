package com.example.ratingsservice.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRatingModel {
    private String userId;
    private List<RatingModel> ratings;
}
