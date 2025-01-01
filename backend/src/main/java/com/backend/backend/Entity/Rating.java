package com.backend.backend.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "rating")
public class Rating {

    @Id
    private String id;
    public Rating(String userId,String pid,int ratingScore) {
        this.userId = userId;
        this.pid = pid;
        this.ratingScore = ratingScore;

        this.id = userId+"_"+pid;
    }
    private String userId;

    private String pid;

    private int ratingScore;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(int ratingScore) {
        this.ratingScore = ratingScore;
    }
}
