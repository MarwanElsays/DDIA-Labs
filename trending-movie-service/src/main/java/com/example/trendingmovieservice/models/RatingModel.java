package com.example.trendingmovieservice.models;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingModel {
    private String movieId;
    private int rating;
}
