package com.backend.backend;

import com.backend.backend.Controller.DestinationController;
import com.backend.backend.Entity.Destination;
import com.backend.backend.Service.DestinationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DestinationControllerTest {

    @Mock
    private DestinationService destinationService;

    @InjectMocks
    private DestinationController destinationController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(destinationController).build();
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
    public void testFetchDestinationById2() throws Exception {
        Destination destination = new Destination("Beach Paradise", "Hawaii", "Event1",
                "Beautiful beach destination", "photoUrl", 200.0, 4.8, 100, "Relaxation");
        destination.setPid("1");

        when(destinationService.getDestinationById("1")).thenReturn(destination);

        String response = mockMvc.perform(get("/destination/1")
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
}
