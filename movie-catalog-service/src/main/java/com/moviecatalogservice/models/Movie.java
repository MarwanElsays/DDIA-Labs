package com.moviecatalogservice.models;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {
    private String movieId;
    private String name;
    private String description;
}
