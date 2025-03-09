package com.example.ratingsservice.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RatingId implements Serializable {

    @Column(nullable = false, updatable = false)
    private String movieId;

    @Column(nullable = false, updatable = false)
    private String userId;
}

