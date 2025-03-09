package com.example.ratingsservice.Repository;

import com.example.ratingsservice.Entity.Rating;
import com.example.ratingsservice.Entity.RatingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, RatingId> {
    @Query(value = "SELECT * FROM rating as r WHERE r.user_id = :userId", nativeQuery = true)
    Optional<List<Rating>> findAllRatingsByUserId(@Param("userId") String userId);
}
