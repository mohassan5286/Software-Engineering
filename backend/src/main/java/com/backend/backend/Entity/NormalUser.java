package com.backend.backend.Entity;

import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class NormalUser extends User {
    List<Booking> bookingHistory;

    public NormalUser(String name, String email, String uid, List<Booking> bookingHistory) {
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.bookingHistory = bookingHistory;
    }


    public List<Booking> getBookingHistory() {
        return bookingHistory;
    }

    public boolean addBooking(Booking booking) {
        return true;
    }

}
