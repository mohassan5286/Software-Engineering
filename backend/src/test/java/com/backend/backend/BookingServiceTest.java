package com.backend.backend;

import com.backend.backend.Entity.Booking;
import com.backend.backend.Entity.Destination;
import com.backend.backend.Entity.User;
import com.backend.backend.Repository.BookingRepository;
import com.backend.backend.Repository.DestinationRepository;
import com.backend.backend.Repository.UserRepository;
import com.backend.backend.Service.BookingService;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;



@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private UserRepository UserRepository;

    @Mock
    private DestinationRepository destinationRepository;

    @Mock
    User User;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    public void setUp() {

        User = new User();

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addBooking_Happy() {
        // Arrange

        Booking booking = new Booking("P12312", "U456", new Date(), 2);

        Mockito.when(User.getBookingHistory()).thenReturn(new ArrayList<>());
        Mockito.when(UserRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(User));
        Mockito.when(UserRepository.save(User)).thenReturn(User);
        Mockito.when(destinationRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(new Destination()));

        // Act
        String result = bookingService.addBooking(booking);

        // Assert
        assertEquals("Booking added successfully!", result);
    }


    @Test
    public void addBooking_sad_1() {
        // Arrange

        Booking booking = new Booking("P12312", "U456", new Date(), 2);

        Mockito.when(UserRepository.findById(Mockito.any(String.class))).thenThrow(new NoSuchElementException("User not found!"));

        // Act
        String result = bookingService.addBooking(booking);

        // Assert
        assertEquals("Error: User not found!", result);
    }

    @Test
    public void addBooking_sad_2() {
        // Arrange

        Booking booking = new Booking("P12312", "U456", new Date(), 2);

        Mockito.when(UserRepository.findById(Mockito.any(String.class))).thenReturn(Optional.of(User));
        Mockito.when(destinationRepository.findById(Mockito.any(String.class))).thenThrow(new NoSuchElementException("Destination not found!"));

        // Act
        String result = bookingService.addBooking(booking);

        // Assert
        assertEquals("Error: Destination not found!", result);
    }

    @Test
    public void addBooking_sad_3() {
        // Arrange

        Booking booking = new Booking("P12312", "U456", new Date(), 2);

        Mockito.when(UserRepository.findById(Mockito.any(String.class))).thenThrow(new DuplicateFormatFlagsException(""));

        // Act
        String result = bookingService.addBooking(booking);

        // Assert
        assertEquals("An unexpected error occurred!", result);
    }
    @Test
    public void getAllBookingsByUid_Happy() {
        // Arrange
        String uid = "U456";
        List<Booking> bookings = Arrays.asList(
                new Booking("P12312", "U456", new Date(), 2),
                new Booking("P78910", "U456", new Date(), 3)
        );

        Mockito.when(bookingRepository.findByUid(uid)).thenReturn(bookings);

        // Act
        List<Booking> result = bookingService.getAllBookingsByUid(uid);

        // Assert
        assertThat(result).isEqualTo(bookings);
    }

    @Test
    public void getAllBookingsByUid_Sad() {
        // Arrange
        String uid = "U456";

        Mockito.when(bookingRepository.findByUid(uid)).thenReturn(Collections.emptyList());

        // Act
        List<Booking> result = bookingService.getAllBookingsByUid(uid);

        // Assert
        assertThat(result).isEmpty();
    }

    @Test
    public void removeBooking_Happy() {
        // Arrange
        String pid = "P12312";
        String uid = "U456";

        Mockito.when(bookingRepository.findByUidAndPid(pid, uid)).thenReturn(Optional.of(new Booking()));

        // Act
        String result = bookingService.removeBooking(pid, uid);

        // Assert
        assertEquals("Booking removed successfully!", result);
    }

    @Test
    public void removeBooking_Sad_BookingNotFound() {
        // Arrange
        String pid = "P12312";
        String uid = "U456";

        Mockito.when(bookingRepository.findByUidAndPid(pid, uid)).thenReturn(Optional.empty());

        // Act
        String result = bookingService.removeBooking(pid, uid);

        // Assert
        assertEquals("Error: Booking not found!", result);
    }
    @Test
    public void addBooking_Happy_Path_IfCondition() {
        // Arrange
        Booking booking = new Booking("P12312", "U456", new Date(), 2);

        Mockito.when(UserRepository.findById("U456")).thenReturn(Optional.of(User));
        Mockito.when(destinationRepository.findById("P12312")).thenReturn(Optional.of(new Destination()));
        Mockito.when(bookingRepository.findByUidAndPid("P12312", "U456")).thenReturn(Optional.of(booking));

        // Act
        String result = bookingService.addBooking(booking);

        // Assert
        assertEquals("Booking added successfully!", result);
        Mockito.verify(bookingRepository).deleteByUidAndPid("P12312", "U456");
        Mockito.verify(UserRepository).save(User);
    }
}
