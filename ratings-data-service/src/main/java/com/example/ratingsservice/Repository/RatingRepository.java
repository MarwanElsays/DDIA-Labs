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
    @Query(value = "SELECT * FROM rating r WHERE r.user_id = :userId", nativeQuery = true)
    Optional<List<Rating>> findAllRatingsByUserId(@Param("userId") String userId);

    @Query(value = "SELECT r.movie_id, AVG(r.rating_value) AS avg_rating " +
            "FROM rating r " +
            "GROUP BY r.movie_id " +
            "ORDER BY avg_rating DESC " +
            "LIMIT 10", nativeQuery = true)
    Optional<List<Object[]>> findTop10ByOrderByRatingValueDesc();

}
