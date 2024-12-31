package com.backend.backend;

import com.backend.backend.Controller.BookingController;
import com.backend.backend.Controller.DestinationController;
import com.backend.backend.Entity.Booking;
import com.backend.backend.Entity.Destination;
import com.backend.backend.Service.BookingService;
import com.backend.backend.Service.DestinationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(MockitoExtension.class)
public class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    private MockMvc mockMvc;

    ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testPostBookingHappy1() throws Exception {
        // Arrange
        Booking booking = new Booking(
                "674f56cca02d3de66bcaebcc",
                "U456",
                new Date(),
                "Pending",
                2
        );

        String bookingJson = objectMapper.writeValueAsString(booking);

        String res = "Booking added successfully!";

        Mockito.when(bookingService.addBooking(Mockito.any(Booking.class))).thenReturn(res);

        // Act
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/booking/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(bookingJson))
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(res, response);

    }

    @Test
    public void testPostBookingSad1_NotFoundUser() throws Exception {
        // Arrange
        Booking booking = new Booking(
                "674f56cca02d3de66bcaebcc",
                "U456",
                new Date(),
                "Pending",
                2
        );

        String bookingJson = objectMapper.writeValueAsString(booking);

        String res = "Error: User not found!";

        Mockito.when(bookingService.addBooking(Mockito.any(Booking.class))).thenReturn(res);

        // Act
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/booking/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(bookingJson))
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(res, response);

    }

    @Test
    public void testPostBookingSad1_NotFoundDestination() throws Exception {
        // Arrange
        Booking booking = new Booking(
                "674f56cca02d3de66bcaebcc",
                "U456",
                new Date(),
                "Pending",
                2
        );

        String bookingJson = objectMapper.writeValueAsString(booking);

        String res = "Error: Destination not found!";

        Mockito.when(bookingService.addBooking(Mockito.any(Booking.class))).thenReturn(res);

        // Act
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/booking/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(bookingJson))
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(res, response);

    }

    @Test
    public void testPostBookingSad1_DuplicateBooking() throws Exception {
        // Arrange
        Booking booking = new Booking(
                "674f56cca02d3de66bcaebcc",
                "U456",
                new Date(),
                "Pending",
                2
        );

        String bookingJson = objectMapper.writeValueAsString(booking);

        String res = "Error: Duplicate Booking Record!";

        Mockito.when(bookingService.addBooking(Mockito.any(Booking.class))).thenReturn(res);

        // Act
        String response = mockMvc.perform(MockMvcRequestBuilders.post("/booking/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(bookingJson))
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(res, response);

    }

    //    Milestone 3

    @Test
    public void testGetAllBookingsHappy1() throws Exception {
        // Arrange
        Booking booking1 = new Booking(
                "674f56cca02d3de66bcaebcc",
                "U456",
                new Date(),
                "Pending",
                2
        );
        Booking booking2 = new Booking(
                "123a456b789c0de123fghijk",
                "U457",
                new Date(),
                "Confirmed",
                3
        );

        List<Booking> bookings = Arrays.asList(booking1, booking2);

        String bookingsJson = objectMapper.writeValueAsString(bookings);

        Mockito.when(bookingService.getAllBookings()).thenReturn(bookings);

        // Act
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/booking/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertEquals(bookingsJson, response);
    }


    @Test
    public void testRemoveBookingHappy1() throws Exception {
        // Arrange
        String bookingId = "674f56cca02d3de66bcaebcc";
        String res = "Booking removed successfully!";  // Expected response message

        Mockito.when(bookingService.removeBooking(Mockito.anyString())).thenReturn(res);

        // Act
        String response = mockMvc.perform(MockMvcRequestBuilders.delete("/booking/remove/{id}", bookingId)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Assert
        assertEquals(res, response);
    }

    @Test
    public void testRemoveBookingNotFound() throws Exception {
        // Arrange
        String bookingId = "nonexistentBookingId";  // An ID that does not exist
        String res = "Booking not found";  // Expected error message

        Mockito.when(bookingService.removeBooking(Mockito.anyString())).thenThrow(new NoSuchElementException("Booking not found"));

        // Act
        String response = mockMvc.perform(MockMvcRequestBuilders.delete("/booking/remove/{id}", bookingId)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Assert
        assertEquals(res, response);
    }


}
