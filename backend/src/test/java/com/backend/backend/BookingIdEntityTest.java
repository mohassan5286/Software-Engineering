package com.backend.backend;

import com.backend.backend.Entity.BookingId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookingIdTest {

    @Test
    void testConstructorAndGetters() {
        // Instantiate a BookingId object
        String uid = "user123";
        String pid = "product456";
        BookingId bookingId = new BookingId(pid, uid);

        // Test values set by the constructor
        assertEquals(uid, bookingId.getUid());
        assertEquals(pid, bookingId.getPid());
    }

    @Test
    void testSetters() {
        // Create a BookingId object
        BookingId bookingId = new BookingId();

        // Set values using setters
        String newUid = "user789";
        String newPid = "product789";
        bookingId.setUid(newUid);
        bookingId.setPid(newPid);

        // Test that the values are updated correctly
        assertEquals(newUid, bookingId.getUid());
        assertEquals(newPid, bookingId.getPid());
    }

    @Test
    void testEqualsMethod() {
        // Create two BookingId objects with the same values
        BookingId bookingId1 = new BookingId("product123", "user123");
        BookingId bookingId2 = new BookingId("product123", "user123");

        // Create another BookingId object with different values
        BookingId bookingId3 = new BookingId("product456", "user123");

        // Test equals
        assertEquals(bookingId1, bookingId2); // Should be equal
        assertNotEquals(bookingId1, bookingId3); // Should not be equal
    }

    @Test
    void testHashCodeMethod() {
        // Create two BookingId objects with the same values
        BookingId bookingId1 = new BookingId("product123", "user123");
        BookingId bookingId2 = new BookingId("product123", "user123");

        // Test hashCode
        assertEquals(bookingId1.hashCode(), bookingId2.hashCode());

        // Create another BookingId object with different values
        BookingId bookingId3 = new BookingId("product456", "user123");

        // Test that hashCode is different
        assertNotEquals(bookingId1.hashCode(), bookingId3.hashCode());
    }

    @Test
    void testDefaultConstructor() {
        // Test default constructor
        BookingId bookingId = new BookingId();
        assertNull(bookingId.getUid());
        assertNull(bookingId.getPid());
    }
}
