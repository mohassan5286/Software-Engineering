package com.backend.backend.Controller;

import com.backend.backend.Entity.Booking;
import com.backend.backend.Service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@CrossOrigin(value = "http://localhost:3000/")
//@AllArgsConstructor
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/add")
    public String fetchDestinationById(@RequestBody Booking booking) {
        return bookingService.addBooking(booking);
    }

    @GetMapping("/all")
    public List<Booking> fetchAllBookings() {
        return bookingService.getAllBookings();
    }

    @DeleteMapping("/remove/{id}")
    public String removeBooking(@PathVariable String id) {
        return bookingService.removeBooking(id);
    }
}
