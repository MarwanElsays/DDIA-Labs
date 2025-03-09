package com.example.movieinfoservice.models;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieModel {
    private String movieId;
    private String name;
    private String description;
}
