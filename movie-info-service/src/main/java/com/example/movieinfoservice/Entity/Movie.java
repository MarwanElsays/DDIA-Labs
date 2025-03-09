package com.example.movieinfoservice.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Builder
@Document(collection = "movie")
public class Movie {
    @Id
    private String movieId;
    private String name;
    private String description;
}
