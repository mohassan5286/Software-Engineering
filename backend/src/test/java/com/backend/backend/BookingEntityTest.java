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
        assertNull(booking.getId().getPid());
        assertNull(booking.getId().getUid());
        assertNull(booking.getBookingDate());
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
                4
        );

        // Test that the values are set correctly
        assertEquals("123", booking.getId().getPid());
        assertEquals("456", booking.getId().getUid());
        assertEquals(bookingDate, booking.getBookingDate());
        assertEquals(4, booking.getNo_of_persons());
    }

    @Test
    void testConstructorWithNullValues() {
        // Test constructor with null values
        Booking booking = new Booking(
                null,
                null,
                null,
                0
        );

        // Test that the fields are correctly initialized with nulls or 0 where appropriate
        assertNull(booking.getId().getPid());
        assertNull(booking.getId().getUid());
        assertNull(booking.getBookingDate());
        assertEquals(0, booking.getNo_of_persons());
    }

    @Test
    void testSettersAndGetters() {
        Booking booking = new Booking();
        Date bookingDate = new Date();

        booking.getId().setPid("789");
        booking.getId().setUid("101112");
        booking.setBookingDate(bookingDate);
        booking.setNo_of_persons(3);

        assertEquals("789", booking.getId().getPid());
        assertEquals("101112", booking.getId().getUid());
        assertEquals(bookingDate, booking.getBookingDate());
        assertEquals(3, booking.getNo_of_persons());
    }
}
