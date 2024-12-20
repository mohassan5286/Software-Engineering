package com.backend.backend.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
//@Data

public class Destination {

    private String pid;

    private String title;

    private String location;

    private String description;

    private String photo_Url;

    private Double price;

    private Double rating;

    private Integer no_of_reviews;

    private String tourism_type;

    private String event;
    public Destination(){}

    public Destination(String title, String location, String event, String description, String photoUrl, double price, double rating, int noOfReviews, String tourism_type) {
        this.title = title;
        this.location = location;
        this.event = event;
        this.description = description;
        this.photo_Url = photoUrl;
        this.price = price;
        this.rating = rating;
        this.no_of_reviews = noOfReviews;
        this.tourism_type = tourism_type;
    }


    public Integer getNo_of_reviews() {
        return no_of_reviews;
    }

    public void setNo_of_reviews(Integer no_of_reviews) {
        this.no_of_reviews = no_of_reviews;
    }

    public String getTourism_type() {
        return tourism_type;
    }

    public void setTourism_type(String tourism_type) {
        this.tourism_type = tourism_type;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPhoto_Url() {
        return photo_Url;
    }

    public void setPhoto_Url(String photo_Url) {
        this.photo_Url = photo_Url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}