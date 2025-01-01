package com.backend.backend.Service;

import com.backend.backend.Entity.Destination;
import com.backend.backend.Entity.Rating;
import com.backend.backend.Repository.DestinationRepository;
import com.backend.backend.Repository.RatingRepository;
import com.backend.backend.Service.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private DestinationRepository destinationRepository;

    @InjectMocks
    private RatingService ratingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRate_HappyPath() {
        // Arrange
        Rating rating = new Rating("U12345", "P67890", 4);
        Destination destination = new Destination();
        destination.setPid("P67890");
        destination.setRating(0.0);
        destination.setNo_of_reviews(0);

        List<Rating> ratings = new ArrayList<>();
        ratings.add(rating);

        Mockito.when(ratingRepository.save(any(Rating.class))).thenReturn(rating);
        Mockito.when(ratingRepository.findByPid("P67890")).thenReturn(ratings);
        Mockito.when(destinationRepository.findDestinationById("P67890")).thenReturn(destination);
        Mockito.when(destinationRepository.save(any(Destination.class))).thenReturn(destination);

        // Act
        ratingService.rate(rating);

        // Assert
        assertEquals(4.0, destination.getRating(), "Destination rating should be updated.");
        assertEquals(1, destination.getNo_of_reviews(), "Number of reviews should be updated.");
    }

    @Test
    public void testGetRating_HappyPath() {
        // Arrange
        Rating rating = new Rating("U12345", "P67890", 4);

        Mockito.when(ratingRepository.findByUserIdAndPid("U12345", "P67890")).thenReturn(rating);

        // Act
        Rating result = ratingService.getRating("U12345", "P67890");

        // Assert
        assertEquals(4.0, result.getRatingScore(), "Rating score should match the expected value.");
        assertEquals("U12345", result.getUserId(), "User ID should match the input.");
        assertEquals("P67890", result.getPid(), "Product ID should match the input.");
    }

    @Test
    public void testGetAverageRating_HappyPath() {
        // Arrange
        Rating rating1 = new Rating("U12345", "P67890", 4);
        Rating rating2 = new Rating("U67890", "P67890", 5);

        List<Rating> ratings = new ArrayList<>();
        ratings.add(rating1);
        ratings.add(rating2);

        Destination destination = new Destination();
        destination.setPid("P67890");
        destination.setRating(0.0);
        destination.setNo_of_reviews(0);

        Mockito.when(ratingRepository.findByPid("P67890")).thenReturn(ratings);
        Mockito.when(destinationRepository.findDestinationById("P67890")).thenReturn(destination);
        Mockito.when(destinationRepository.save(any(Destination.class))).thenReturn(destination);

        // Act
        double averageRating = ratingService.getAverageRating("P67890");

        // Assert
        assertEquals(4.5, averageRating, "Average rating should be calculated correctly.");
        assertEquals(4.5, destination.getRating(), "Destination rating should be updated.");
        assertEquals(2, destination.getNo_of_reviews(), "Number of reviews should match the ratings count.");
    }

    @Test
    public void testGetAverageRating_EmptyRatings() {
        // Arrange
        Mockito.when(ratingRepository.findByPid("P67890")).thenReturn(new ArrayList<>());
        Mockito.when(destinationRepository.findDestinationById("P67890")).thenReturn(new Destination());

        // Act
        double averageRating = ratingService.getAverageRating("P67890");

        // Assert
        assertEquals(0, averageRating, "Average rating should be 0 if no ratings are present.");
    }

    @Test
    public void testRate_NoDestinationFound() {
        // Arrange
        Rating rating = new Rating("U12345", "P67890", 4);

        Mockito.when(ratingRepository.save(any(Rating.class))).thenReturn(rating);
        Mockito.when(destinationRepository.findDestinationById("P67890")).thenReturn(null);

        // Act & Assert
        try {
            ratingService.rate(rating);
        } catch (RuntimeException e) {
            assertEquals("Destination not found", e.getMessage(), "Exception should be thrown if destination is missing.");
        }
    }
}
