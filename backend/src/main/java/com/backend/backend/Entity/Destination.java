package com.backend.backend.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
//@Data
@NoArgsConstructor
public class Destination {
    @Id @Getter @Setter
    private String pid;
    @Getter @Setter
    private String title;
    @Getter @Setter
    private String location;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private String photo_Url;
    @Getter @Setter
    private Double price;
    @Getter @Setter
    private Double rating;
    @Getter @Setter
    private Integer no_of_reviews;

    @Getter @Setter
    private String tourism_type;
    @Getter @Setter
    private String event;
    public Destination(){

    }
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
}
