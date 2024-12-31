package com.backend.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
//
//@Setter
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
@Document(collection = "booking")
public class Booking {

    @Id
    private BookingId id;  // Use the composite key (uid + pid)
    private Date bookingDate;
    private int no_of_persons;

    public Booking() {
    }

    public Booking(String pid, String uid, Date bookingDate, int no_of_persons) {
        this.id = new BookingId(pid, uid); // Set the composite ID
        this.bookingDate = bookingDate;
        this.no_of_persons = no_of_persons;
    }

    // Getters and setters
    public BookingId getId() {
        return id;
    }

    public void setId(BookingId id) {
        this.id = id;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getNo_of_persons() {
        return no_of_persons;
    }

    public void setNo_of_persons(int no_of_persons) {
        this.no_of_persons = no_of_persons;
    }
}
