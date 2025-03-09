package com.example.ratingsservice.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingModel {
    private String movieId;
    private int rating;
}
