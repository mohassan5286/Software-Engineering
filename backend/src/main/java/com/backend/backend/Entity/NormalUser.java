package com.backend.backend.Entity;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
//@Getter
public class NormalUser {
    @Id
    String uid = "";
    String name = "";
    String email = "";
    String password;

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

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
