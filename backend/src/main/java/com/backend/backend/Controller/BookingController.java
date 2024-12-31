package com.backend.backend.Controller;

import com.backend.backend.Entity.Booking;
import com.backend.backend.Service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@CrossOrigin
//@AllArgsConstructor
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/add")
    public String fetchDestinationById(@RequestBody Booking booking) {
        return bookingService.addBooking(booking);
    }
}
