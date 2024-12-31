package com.backend.backend.Entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

public class BookingId implements Serializable {
    private String uid;
    private String pid;

    public BookingId() {
    }

    public BookingId(String uid, String pid) {
        this.uid = uid;
        this.pid = pid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingId that = (BookingId) o;
        return Objects.equals(uid, that.uid) && Objects.equals(pid, that.pid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid, pid);
    }
}

