package com.backend.backend;

import com.backend.backend.Controller.DestinationController;
import com.backend.backend.Entity.Destination;
import com.backend.backend.Service.DestinationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

@ExtendWith(MockitoExtension.class)
public class DestinationControllerTest {

    @Mock
    private DestinationService destinationService;

    @InjectMocks
    private DestinationController destinationController;

    private MockMvc mockMvc;
    private List<Destination> destinations;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(destinationController).build();
        destinations = Arrays.asList(
                new Destination("Beach Paradise", "Hawaii", "Event1", "Beautiful beach destination", "photoUrl", 200.0, 4.8, 100, "Relaxation"),
                new Destination("Mountain Retreat", "Alps", "Event2", "Peaceful mountain retreat", "photoUrl", 150.0, 4.5, 50, "Adventure"),
                new Destination("City Explorer", "Paris", "Event3", "Explore the cultural wonders of Paris", "photoUrl", 250.0, 4.7, 75, "Cultural")
        );
    }

    @Test
    public void testFetchDestinationById() throws Exception {
        Destination destination = new Destination("Beach Paradise", "Hawaii", "Event1",
                "Beautiful beach destination", "photoUrl", 200.0, 4.8, 100, "Relaxation");
        destination.setPid("1");

        when(destinationService.getDestinationById("1")).thenReturn(destination);

        String response = mockMvc.perform(get("/destination/get/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue(response.contains("\"pid\":\"1\""));
        assertTrue(response.contains("\"title\":\"Beach Paradise\""));
    }

    @Test
    public void testFetchDestinationAll() throws Exception {
        Destination destination1 = new Destination("Beach Paradise", "Hawaii", "Event1",
                "Beautiful beach destination", "photoUrl", 200.0, 4.8, 100, "Relaxation");
        Destination destination2 = new Destination("Mountain Retreat", "Alps", "Event2",
                "Peaceful mountain retreat", "photoUrl", 150.0, 4.5, 50, "Adventure");

        List<Destination> destinations = Arrays.asList(destination1, destination2);
        when(destinationService.getDestinationAll()).thenReturn(destinations);

        String response = mockMvc.perform(get("/destination/get/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue(response.contains("\"title\":\"Beach Paradise\""));
        assertTrue(response.contains("\"location\":\"Alps\""));
    }

    @Test
    public void testGetAllDestinationsWithSelectedFields() throws Exception {
        Destination destination = new Destination("Beach Paradise", "Hawaii", "Event1",
                "Beautiful beach destination", "photoUrl", 200.0, 4.8, 100, "Relaxation");
        List<Destination> destinations = List.of(destination);

        when(destinationService.getAllDestinationsWithSelectedFields()).thenReturn(destinations);

        String response = mockMvc.perform(get("/destination")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue(response.contains("\"title\":\"Beach Paradise\""));
    }

    @Test
    public void testGetDestinationsByTourismType() throws Exception {
        Destination destination = new Destination("Beach Paradise", "Hawaii", "Event1",
                "Beautiful beach destination", "photoUrl", 200.0, 4.8, 100, "Relaxation");
        List<Destination> destinations = List.of(destination);

        when(destinationService.getDestinationsByCategoryWithSelectedFields("Relaxation")).thenReturn(destinations);

        String response = mockMvc.perform(get("/destination/category/Relaxation")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertTrue(response.contains("\"tourism_type\":\"Relaxation\""));
    }

    @Test
    public void searchByTitle_endpointTest() throws Exception {
        when(destinationService.searchByTitle("beach")).thenReturn(List.of(destinations.get(0)));

        mockMvc.perform(get("/destination/search?title=beach")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title").value("Beach Paradise"));
    }

    @Test
    public void filterDestinations_endpointTest() throws Exception {
        when(destinationService.filterDestinations("Hawaii", "Relaxation", 250.0))
                .thenReturn(List.of(destinations.get(0)));

        mockMvc.perform(get("/destination/filter?location=Hawaii&tourism_type=Relaxation&maxPrice=250")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].location").value("Hawaii"));
    }

    @Test
    public void filterDestinations_endpointTest_byLocationAndPrice() throws Exception {
        when(destinationService.filterDestinations("Alps", null, 160.0))
                .thenReturn(List.of(destinations.get(1)));

        mockMvc.perform(get("/destination/filter?location=Alps&maxPrice=160")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].location").value("Alps"));
    }

    @Test
    public void filterDestinations_endpointTest_noResults() throws Exception {
        when(destinationService.filterDestinations("Hawaii", "Relaxation", 100.0))
                .thenReturn(Collections.emptyList());

        mockMvc.perform(get("/destination/filter?location=Hawaii&tourism_type=Relaxation&maxPrice=100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));  // No results expected
    }
}
