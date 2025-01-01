package com.backend.backend;

import com.backend.backend.Entity.User;
import com.backend.backend.Entity.Booking;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    @Test
    void testConstructorAndDefaultValues() {
        // Create an empty booking history list
        List<Booking> emptyBookingHistory = new ArrayList<>();

        // Instantiate a User using the all-args constructor
        User user = new User(
                "1", // id
                "Alice", // username
                "password123", // password
                "alice@example.com", // email
                "token123", // verificationToken
                false, // enabled
                emptyBookingHistory // bookingHistory
        );

        // Test the values set by the constructor
        assertEquals("1", user.getId());
        assertEquals("Alice", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("alice@example.com", user.getEmail());
        assertEquals("token123", user.getVerificationToken());
        assertFalse(user.isEnabled());
        assertEquals(emptyBookingHistory, user.getBookingHistory());
    }

    @Test
    void testSetAndGetVerificationToken() {
        User user = new User();
        user.setVerificationToken("newToken");
        assertEquals("newToken", user.getVerificationToken());
    }

    @Test
    void testEnableUser() {
        User user = new User();
        user.setEnabled(true);
        assertTrue(user.isEnabled());
    }

    @Test
    void testGetBookingHistory() {
        // Create a mock booking history
        List<Booking> bookingHistory = new ArrayList<>();
        Booking booking1 = new Booking(); // Assuming Booking has a no-args constructor
        Booking booking2 = new Booking();
        bookingHistory.add(booking1);
        bookingHistory.add(booking2);

        // Create a User with a booking history
        User user = new User(
                "2",
                "Bob",
                "password123",
                "bob@example.com",
                "token456",
                true,
                bookingHistory
        );

        // Test the getter for booking history
        assertEquals(bookingHistory, user.getBookingHistory());
        assertEquals(2, user.getBookingHistory().size());
        assertTrue(user.getBookingHistory().contains(booking1));
        assertTrue(user.getBookingHistory().contains(booking2));
    }

    @Test
    void testSetAndGetEmail() {
        User user = new User();
        user.setEmail("test@example.com");
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    void testSetAndGetUsername() {
        User user = new User();
        user.setUsername("testUser");
        assertEquals("testUser", user.getUsername());
    }
}
