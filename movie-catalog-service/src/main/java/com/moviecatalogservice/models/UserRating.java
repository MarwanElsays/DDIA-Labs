package com.moviecatalogservice.models;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRating {
    private String userId;
    private List<Rating> ratings;
}
