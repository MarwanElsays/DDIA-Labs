package com.moviecatalogservice.models;

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
