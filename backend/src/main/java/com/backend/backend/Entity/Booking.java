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
@Document(collation = "booking")
//@AllArgsConstructor
//@NoArgsConstructor
@CompoundIndex(def = "{'pid': 1, 'uid': 1}", unique = true)
public class Booking {
//    @Id
    String pid;
//    @Indexed(unique = true)
//    @Indexed(unique = true)
    String uid;
    Date bookingDate;
    int no_of_persons;

    public Booking() {
    }

    public Booking(String pid, String uid, Date bookingDate, int no_of_persons) {
        this.pid = pid;
        this.uid = uid;
        this.bookingDate = bookingDate;
        this.no_of_persons = no_of_persons;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

