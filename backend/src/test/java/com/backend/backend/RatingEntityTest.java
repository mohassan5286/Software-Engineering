package com.backend.backend;

import com.backend.backend.Entity.Rating;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RatingEntityTest {

    @Test
    void testConstructorAndIdGeneration() {
        // Instantiate a Rating object
        String userId = "user123";
        String pid = "product456";
        int ratingScore = 5;

        Rating rating = new Rating(userId, pid, ratingScore);

        // Test the values set by the constructor
        assertEquals(userId, rating.getUserId());
        assertEquals(pid, rating.getPid());
        assertEquals(ratingScore, rating.getRatingScore());

        // Test that the ID is generated correctly
        assertEquals(userId + "_" + pid, rating.getId());
    }

    @Test
    void testSetAndGetUserId() {
        Rating rating = new Rating("user123", "product456", 5);

        // Set a new userId
        String newUserId = "user789";
        rating.setUserId(newUserId);

        // Test that the userId was updated correctly
        assertEquals(newUserId, rating.getUserId());
    }

    @Test
    void testSetAndGetPid() {
        Rating rating = new Rating("user123", "product456", 5);

        // Set a new pid
        String newPid = "product789";
        rating.setPid(newPid);

        // Test that the pid was updated correctly
        assertEquals(newPid, rating.getPid());
    }

    @Test
    void testSetAndGetRatingScore() {
        Rating rating = new Rating("user123", "product456", 5);

        // Set a new rating score
        int newRatingScore = 4;
        rating.setRatingScore(newRatingScore);

        // Test that the rating score was updated correctly
        assertEquals(newRatingScore, rating.getRatingScore());
    }

    @Test
    void testSetAndGetId() {
        Rating rating = new Rating("user123", "product456", 5);

        // Set a new ID
        String newId = "custom_id";
        rating.setId(newId);

        // Test that the ID was updated correctly
        assertEquals(newId, rating.getId());
    }
}
