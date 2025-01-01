package com.backend.backend;

import com.backend.backend.Entity.Destination;
import com.backend.backend.Entity.Rating;
import com.backend.backend.Repository.DestinationRepository;
import com.backend.backend.Repository.RatingRepository;
import com.backend.backend.Service.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RatingServiceTest {

    @InjectMocks
    private RatingService ratingService;

    @Mock
    private RatingRepository ratingRepo;

    @Mock
    private DestinationRepository destinationRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRate() {
        // Arrange
        Rating mockRating = new Rating("user123", "pid123", 4);

        // Act
        ratingService.rate(mockRating);

        // Assert
        verify(ratingRepo, times(1)).save(mockRating);
        verify(ratingRepo, times(1)).findByPid("pid123");
        verify(destinationRepo, times(1)).findDestinationById("pid123");
    }

    @Test
    void testGetRating() {
        // Arrange
        String userId = "user123";
        String pid = "pid123";
        Rating mockRating = new Rating(userId, pid, 5);

        when(ratingRepo.findByUserIdAndPid(userId, pid)).thenReturn(mockRating);

        // Act
        Rating result = ratingService.getRating(userId, pid);

        // Assert
        assertNotNull(result);
        assertEquals(mockRating, result);
        verify(ratingRepo, times(1)).findByUserIdAndPid(userId, pid);
    }

    @Test
    void testGetAverageRating() {
        // Arrange
        String pid = "pid123";
        List<Rating> mockRatings = Arrays.asList(
                new Rating("user1", pid, 4),
                new Rating("user2", pid, 5),
                new Rating("user3", pid, 3)
        );
        Destination mockDestination = new Destination();
        mockDestination.setPid(pid);

        when(ratingRepo.findByPid(pid)).thenReturn(mockRatings);
        when(destinationRepo.findDestinationById(pid)).thenReturn(mockDestination);

        // Act
        double averageRating = ratingService.getAverageRating(pid);

        // Assert
        assertEquals(4.0, averageRating, 0.01);
        assertEquals(4.0, mockDestination.getRating(), 0.01);
        assertEquals(3, mockDestination.getNo_of_reviews());
        verify(ratingRepo, times(1)).findByPid(pid);
        verify(destinationRepo, times(1)).findDestinationById(pid);
        verify(destinationRepo, times(1)).save(mockDestination);
    }

    @Test
    void testGetAverageRatingHandlesException() {
        // Arrange
        String pid = "pid123";

        when(ratingRepo.findByPid(pid)).thenThrow(new RuntimeException("Database error"));

        // Act
        double averageRating = ratingService.getAverageRating(pid);

        // Assert
        assertEquals(0, averageRating);
        verify(ratingRepo, times(1)).findByPid(pid);
    }

    @Test
    void testRateIntegrationWithGetAverageRating() {
        // Arrange
        String pid = "pid123";
        Rating mockRating = new Rating("user1", pid, 5);
        List<Rating> mockRatings = Arrays.asList(mockRating);
        Destination mockDestination = new Destination();
        mockDestination.setPid(pid);

        when(ratingRepo.findByPid(pid)).thenReturn(mockRatings);
        when(destinationRepo.findDestinationById(pid)).thenReturn(mockDestination);

        // Act
        ratingService.rate(mockRating);

        // Assert
        verify(ratingRepo, times(1)).save(mockRating);
        verify(ratingRepo, times(1)).findByPid(pid);
        verify(destinationRepo, times(1)).findDestinationById(pid);
        verify(destinationRepo, times(1)).save(mockDestination);
    }
}
