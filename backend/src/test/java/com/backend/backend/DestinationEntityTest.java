package com.backend.backend;

import com.backend.backend.Entity.Destination;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DestinationEntityTest {

    @Test
    void testNoArgsConstructor() {
        Destination destination = new Destination();

        // Test that the object is not null
        assertNotNull(destination);

        // Test that all fields are initialized to their default values
        assertNull(destination.getPid());
        assertNull(destination.getTitle());
        assertNull(destination.getLocation());
        assertNull(destination.getDescription());
        assertNull(destination.getPhoto_Url());
        assertNull(destination.getPrice());
        assertNull(destination.getRating());
        assertNull(destination.getNo_of_reviews());
        assertNull(destination.getTourism_type());
        assertNull(destination.getEvent());
    }

    @Test
    void testAllArgsConstructor() {
        // Using all-args constructor to create an object
        Destination destination = new Destination(
                "Paris",
                "France",
                "Festival",
                "A beautiful city.",
                "http://example.com/photo.jpg",
                1200.50,
                4.8,
                120,
                "Urban Tourism"
        );

        // Test that the values are set correctly
        assertEquals("Paris", destination.getTitle());
        assertEquals("France", destination.getLocation());
        assertEquals("Festival", destination.getEvent());
        assertEquals("A beautiful city.", destination.getDescription());
        assertEquals("http://example.com/photo.jpg", destination.getPhoto_Url());
        assertEquals(1200.50, destination.getPrice());
        assertEquals(4.8, destination.getRating());
        assertEquals(120, destination.getNo_of_reviews());
        assertEquals("Urban Tourism", destination.getTourism_type());
    }

    @Test
    void testConstructorWithNullValues() {
        // Test constructor with null values (useful for checking how the system handles null inputs)
        Destination destination = new Destination(
                null,
                null,
                null,
                null,
                null,
                0.0,
                0.0,
                0,
                null
        );

        // Test that the fields are correctly initialized with nulls or 0 where appropriate
        assertNull(destination.getTitle());
        assertNull(destination.getLocation());
        assertNull(destination.getEvent());
        assertNull(destination.getDescription());
        assertNull(destination.getPhoto_Url());
        assertEquals(0.0, destination.getPrice());
        assertEquals(0.0, destination.getRating());
        assertEquals(0, destination.getNo_of_reviews());
        assertNull(destination.getTourism_type());
    }

    @Test
    void testSettersAndGetters() {
        Destination destination = new Destination();

        destination.setTitle("Tokyo");
        destination.setLocation("Japan");
        destination.setEvent("Cherry Blossom");
        destination.setDescription("An amazing place to visit.");
        destination.setPhoto_Url("http://example.com/photo2.jpg");
        destination.setPrice(1500.75);
        destination.setRating(4.9);
        destination.setNo_of_reviews(250);
        destination.setTourism_type("Cultural Tourism");

        assertEquals("Tokyo", destination.getTitle());
        assertEquals("Japan", destination.getLocation());
        assertEquals("Cherry Blossom", destination.getEvent());
        assertEquals("An amazing place to visit.", destination.getDescription());
        assertEquals("http://example.com/photo2.jpg", destination.getPhoto_Url());
        assertEquals(1500.75, destination.getPrice());
        assertEquals(4.9, destination.getRating());
        assertEquals(250, destination.getNo_of_reviews());
        assertEquals("Cultural Tourism", destination.getTourism_type());
    }
}
