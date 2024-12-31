package com.backend.backend.Service;

import com.backend.backend.Entity.Booking;
import com.backend.backend.Entity.User;
import com.backend.backend.Entity.User;
import com.backend.backend.Repository.BookingRepository;
import com.backend.backend.Repository.DestinationRepository;
import com.backend.backend.Repository.UserRepository;
import com.backend.backend.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
//@AllArgsConstructor
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;
@Autowired
    private DestinationRepository destinationRepository;

    public String addBooking(Booking booking) {

        try {
            User user = userRepository.findById(booking.getUid()).orElseThrow(() -> new NoSuchElementException("User not found!"));
//            destinationRepository.findById(booking.getPid()).orElseThrow(() -> new NoSuchElementException("Destination not found!"));
            System.out.println(booking);
            bookingRepository.insert(booking);
            user.getBookingHistory().add(booking);
            userRepository.save(user);
            return "Booking added successfully!";
        } catch (NoSuchElementException e) {
            return "Error: " + e.getMessage();
//        } catch (Dup e) {
//            return "Error: " + e.getMessage();
        } catch (Exception e) {
            return "An unexpected error occurred!";
        }
    }

    public List<Booking> getAllBookings() {

        return bookingRepository.findAll();

    }

    public String removeBooking(String id) {

        try {
            if( bookingRepository.existsById(id) ) {
                bookingRepository.deleteById(id);
                return "Booking removed successfully!";
            } else {
                throw new NoSuchElementException("Booking not found!");
            }
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }

    }
}
