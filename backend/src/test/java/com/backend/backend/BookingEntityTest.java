package com.backend.backend;

import com.backend.backend.Entity.Booking;
import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

public class BookingEntityTest {

    @Test
    void testNoArgsConstructor() {
        Booking booking = new Booking();

        // Test that the object is not null
        assertNotNull(booking);

        // Test that all fields are initialized to their default values
        assertNull(booking.getPid());
        assertNull(booking.getUid());
        assertNull(booking.getBookingDate());
        assertNull(booking.getStatus());
        assertEquals(0, booking.getNo_of_persons());
    }

    @Test
    void testAllArgsConstructor() {
        Date bookingDate = new Date();

        // Using all-args constructor to create an object
        Booking booking = new Booking(
                "123",
                "456",
                bookingDate,
                "Confirmed",
                4
        );

        // Test that the values are set correctly
        assertEquals("123", booking.getPid());
        assertEquals("456", booking.getUid());
        assertEquals(bookingDate, booking.getBookingDate());
        assertEquals("Confirmed", booking.getStatus());
        assertEquals(4, booking.getNo_of_persons());
    }

    @Test
    void testConstructorWithNullValues() {
        // Test constructor with null values
        Booking booking = new Booking(
                null,
                null,
                null,
                null,
                0
        );

        // Test that the fields are correctly initialized with nulls or 0 where appropriate
        assertNull(booking.getPid());
        assertNull(booking.getUid());
        assertNull(booking.getBookingDate());
        assertNull(booking.getStatus());
        assertEquals(0, booking.getNo_of_persons());
    }

    @Test
    void testSettersAndGetters() {
        Booking booking = new Booking();
        Date bookingDate = new Date();

        booking.setPid("789");
        booking.setUid("101112");
        booking.setBookingDate(bookingDate);
        booking.setStatus("Pending");
        booking.setNo_of_persons(3);

        assertEquals("789", booking.getPid());
        assertEquals("101112", booking.getUid());
        assertEquals(bookingDate, booking.getBookingDate());
        assertEquals("Pending", booking.getStatus());
        assertEquals(3, booking.getNo_of_persons());
    }
}
