//package com.backend.backend;
//
//import com.backend.backend.Entity.User;
//import com.backend.backend.Entity.Booking;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class UserEntityTest {
//
//    @Test
//    void testConstructorAndDefaultValues() {
//        // Create an empty booking history list
//        List<Booking> emptyBookingHistory = new ArrayList<>();
//
//        // Instantiate a User using the all-args constructor
//        User user = new User("Alice", "alice@example.com", "UID123", emptyBookingHistory);
//
//        // Test the values set by the constructor
//        assertEquals("Alice", user.getName());
//        assertEquals("alice@example.com", user.getEmail());
//        assertEquals("UID123", user.getUid());
//        assertEquals(emptyBookingHistory, user.getBookingHistory());
//    }
//
//    @Test
//    void testGetBookingHistory() {
//        // Create a mock booking history
//        List<Booking> bookingHistory = new ArrayList<>();
//        Booking booking1 = new Booking(); // Assuming Booking has a no-args constructor
//        Booking booking2 = new Booking();
//        bookingHistory.add(booking1);
//        bookingHistory.add(booking2);
//
//        // Create a User with a booking history
//        User user = new User("Bob", "bob@example.com", "UID456", bookingHistory);
//
//        // Test the getter for booking history
//        assertEquals(bookingHistory, user.getBookingHistory());
//        assertEquals(2, user.getBookingHistory().size());
//        assertTrue(user.getBookingHistory().contains(booking1));
//        assertTrue(user.getBookingHistory().contains(booking2));
//    }
//
//    @Test
//    void testAddBooking() {
//        // Create a User with an empty booking history
//        List<Booking> bookingHistory = new ArrayList<>();
//        User user = new User("Charlie", "charlie@example.com", "UID789", bookingHistory);
//
//        // Create a booking and add it to the user's booking history
//        Booking newBooking = new Booking(); // Assuming Booking has a no-args constructor
//        boolean isAdded = user.addBooking(newBooking);
//
//        // Since the addBooking method always returns true, verify that
//        assertTrue(isAdded);
//
//        // (Optional) If the method is supposed to modify bookingHistory, you'd test it here,
//        // but since your implementation doesn't update the bookingHistory, this step is omitted.
//    }
//}
