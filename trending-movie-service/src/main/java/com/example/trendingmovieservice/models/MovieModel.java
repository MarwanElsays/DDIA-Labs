package com.example.trendingmovieservice.models;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieModel {
    private String movieId;
    private String name;
    private String description;
}