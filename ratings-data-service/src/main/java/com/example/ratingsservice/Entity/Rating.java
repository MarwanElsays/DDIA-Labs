package com.example.ratingsservice.Entity;

import lombok.*;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "rating")
public class Rating {

    @EmbeddedId
    private RatingId ratingId;

    private int ratingValue;
}