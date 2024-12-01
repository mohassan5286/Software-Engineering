package com.backend.backend;

import com.backend.backend.Controller.DestinationController;
import com.backend.backend.Entity.Destination;
import com.backend.backend.Repository.DestinationRepository;
import com.backend.backend.Service.DestinationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//  run with mockito only
@RunWith(MockitoJUnitRunner.class)
public class DestinationControllerTest {

//  resposible for requests
    private MockMvc mockMvc;

//    converting json to strings and vise versa
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

//    What we are going to mock
    @Mock
    private DestinationRepository destinationRepository;

//  Destination Book is accepting DestinationRepository as a mock
    @InjectMocks
    private DestinationController destinationController;

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
//      initiales mockito
        MockitoAnnotations.initMocks(this);
//      Setup mockMvc to get from mockito not tomcat
        this.mockMvc = MockMvcBuilders.standaloneSetup(destinationController).build();
    }

    @Test
    public void getAllRecordsHappy1() {
//      when find all is called -> return mocked destiantions
//        Mockito.when(destinationRepository.findAll()).thenReturn(destinations);
//      simulation of get request
        try {
            mockMvc.perform(
                            MockMvcRequestBuilders
                                    .get("/destination/get/all")
                                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect();
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }

    }
}
