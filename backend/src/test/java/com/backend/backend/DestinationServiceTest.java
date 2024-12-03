package com.backend.backend;

import com.backend.backend.Controller.DestinationController;
import com.backend.backend.Entity.Destination;
import com.backend.backend.Repository.DestinationRepository;
import com.backend.backend.Service.DestinationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class DestinationServiceTest {

    //    converting json to strings and vise versa
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    //    What we are going to mock
    @Mock
    private DestinationRepository destinationRepository;

    //  Destination Book is accepting DestinationRepository as a mock
    @InjectMocks
    private DestinationService destinationService;

    Destination sunnyBeachEscape = new Destination(
            "Sunny Beach Escape",
            "Malibu, California",
            "A beautiful beach destination with golden sands and vibrant sunsets.",
            "https://example.com/images/sunny_beach_escape.jpg",
            150.0,
            4.5,
            120
    );

    Destination mountainRetreat = new Destination(
            "Mountain Retreat",
            "Aspen, Colorado",
            "Cozy cabins surrounded by snow-capped mountains and scenic hiking trails.",
            "https://example.com/images/mountain_retreat.jpg",
            200.0,
            4.8,
            250
    );

    Destination urbanAdventure = new Destination(
            "Urban Adventure",
            "New York City, New York",
            "An exciting city experience with iconic landmarks and vibrant nightlife.",
            "https://example.com/images/urban_adventure.jpg",
            300.0,
            4.3,
            310
    );

    Destination desertOasis = new Destination(
            "Desert Oasis",
            "Dubai, UAE",
            "Luxurious desert experience with sand dunes and a cultural twist.",
            "https://example.com/images/desert_oasis.jpg",
            450.0,
            4.7,
            180
    );

    Destination tropicalParadise = new Destination(
            "Tropical Paradise",
            "Bali, Indonesia",
            "A serene tropical escape with lush greenery and crystal-clear waters.",
            "https://example.com/images/tropical_paradise.jpg",
            220.0,
            4.9,
            400
    );

    List<Destination> destinations = new ArrayList<>(Arrays.asList(sunnyBeachEscape, tropicalParadise, desertOasis, urbanAdventure, mountainRetreat));

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllRecordsHappy1() {
        // Arrange
        Mockito.when(destinationRepository.findAll()).thenReturn(destinations);

        // Act
        List<Destination> result = destinationService.getDestinationAll();

        // Assert
        assertEquals(5, result.size());
    }

    @Test
    public void getRecordHappy1() {
        // Arrange
        Mockito.when(destinationRepository.findById("0")).thenReturn(Optional.ofNullable(destinations.get(0)));

        // Act
        Destination result = destinationService.getDestinationById("0");

        // Assert
        assertEquals("Sunny Beach Escape", result.getTitle());
    }

    @Test
    public void getRecordSad1() {
        // Arrange
        Mockito.when(destinationRepository.findById("-1")).thenReturn(Optional.of(new Destination()));

        // Act
        Destination result = destinationService.getDestinationById("-1");

        // Assert
        assertEquals(null, result.getTitle());
    }

}
