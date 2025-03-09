package com.example.trendingmovieservice.models;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {
    private String movieId;
    private int rating;
}
