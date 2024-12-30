package com.backend.backend.Repository;

import com.backend.backend.Entity.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {

    // Custom query to find a rating by userId and pid (productId)
    @Query("{ 'userId' : ?0, 'pid' : ?1 }")
    Rating findByUserIdAndPid(String userId, String pid);

    // Custom query to find ratings by userId
    @Query("{ 'userId' : ?0 }")
    List<Rating> findByUserId(String userId);

    // Custom query to find ratings by pid (productId)
    @Query("{ 'pid' : ?0 }")
    List<Rating> findByPid(String pid);

    // Custom query to find ratings with a specific ratingScore
    @Query("{ 'ratingScore' : ?0 }")
    List<Rating> findByRatingScore(int ratingScore);

    // Custom query to find ratings with a ratingScore greater than or equal to a value
    @Query("{ 'ratingScore' : { $gte: ?0 } }")
    List<Rating> findByRatingScoreGreaterThanEqual(int ratingScore);

    // Custom query to find ratings with a ratingScore less than or equal to a value
    @Query("{ 'ratingScore' : { $lte: ?0 } }")
    List<Rating> findByRatingScoreLessThanEqual(int ratingScore);

    // Custom query to find ratings with ratingScore in a range (between two values)
    @Query("{ 'ratingScore' : { $gte: ?0, $lte: ?1 } }")
    List<Rating> findByRatingScoreBetween(int minScore, int maxScore);
}
