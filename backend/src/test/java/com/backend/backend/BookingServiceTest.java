package com.backend.backend;

import com.backend.backend.Entity.Booking;
import com.backend.backend.Entity.Destination;
import com.backend.backend.Entity.NormalUser;
import com.backend.backend.Entity.User;
import com.backend.backend.Repository.BookingRepository;
import com.backend.backend.Repository.DestinationRepository;
import com.backend.backend.Repository.NormalUserRepository;
import com.backend.backend.Service.BookingService;
import com.backend.backend.Service.DestinationService;
import org.junit.Before;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;



@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private NormalUserRepository normalUserRepository;

    @Mock
    private DestinationRepository destinationRepository;

    @Mock
    NormalUser normalUser;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    public void setUp() {

        normalUser = new NormalUser(
                "John Doe", "johndoe@example.com", "U456",  new ArrayList<>()
        );

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addBooking_Happy() {
        // Arrange

        Booking booking = new Booking();
        booking.setPid("P12312");
        booking.setUid("U456");
        booking.setBookingDate(new Date());
        booking.setStatus("Confirmed");
        booking.setNo_of_persons(2);
        ;

        Mockito.when(normalUser.getBookingHistory()).thenReturn(new ArrayList<>());
        Mockito.when(normalUserRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(normalUser));
        Mockito.when(normalUserRepository.save(normalUser)).thenReturn(normalUser);
        Mockito.when(destinationRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(new Destination()));

        // Act
        String result = bookingService.addBooking(booking);

        // Assert
        assertEquals("Booking added successfully!", result);
    }


    @Test
    public void addBooking_sad_1() {
        // Arrange

        Booking booking = new Booking();
        booking.setPid("P12312");
        booking.setUid("U456");
        booking.setBookingDate(new Date());
        booking.setStatus("Confirmed");
        booking.setNo_of_persons(2);

        Mockito.when(normalUserRepository.findById(Mockito.any(String.class))).thenThrow(new NoSuchElementException("User not found!"));

        // Act
        String result = bookingService.addBooking(booking);

        // Assert
        assertEquals("Error: User not found!", result);
    }

    @Test
    public void addBooking_sad_2() {
        // Arrange

        Booking booking = new Booking();
        booking.setPid("P12312");
        booking.setUid("U456");
        booking.setBookingDate(new Date());
        booking.setStatus("Confirmed");
        booking.setNo_of_persons(2);

        Mockito.when(normalUserRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(normalUser));
        Mockito.when(destinationRepository.findById(Mockito.any(String.class))).thenThrow(new NoSuchElementException("Destination not found!"));


        // Act
        String result = bookingService.addBooking(booking);

        // Assert
        assertEquals("Error: Destination not found!", result);
    }

    @Test
    public void addBooking_sad_3() {
        // Arrange

        Booking booking = new Booking();
        booking.setPid("P12312");
        booking.setUid("U456");
        booking.setBookingDate(new Date());
        booking.setStatus("Confirmed");
        booking.setNo_of_persons(2);

        Mockito.when(normalUserRepository.findById(Mockito.any(String.class))).thenThrow(new DuplicateFormatFlagsException(""));


        // Act
        String result = bookingService.addBooking(booking);

        // Assert
        assertEquals("An unexpected error occurred!", result);
    }

}
