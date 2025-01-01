package com.backend.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private String verificationToken;  // New field for storing the verification token
    private boolean enabled = false;  // Field to store whether the account is activated
    private List<Booking> bookingHistory;

    public User() {

    }

    public User(String id, String username, String password, String email, String verificationToken, boolean enabled, List<Booking> bookingHistory) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.verificationToken = verificationToken;
        this.enabled = enabled;
        this.bookingHistory = bookingHistory;
    }

    public String getId() {
        return id;
    }

    // Getter for username
    public String getUsername() {
        return username;
    }

    // Setter for username
    public void setUsername(String username) {
        this.username = username;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }


    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }
    // Getters and Setters

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public List<Booking> getBookingHistory() {
        return bookingHistory;
    }
}
