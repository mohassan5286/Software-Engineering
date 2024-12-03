package com.backend.backend.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
public class Destination {
    @Id
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

    public Destination(String title, String location, String event, String description, String photoUrl, double price, double rating, int noOfReviews, String tourismType) {
        this.title = title;
        this.location = location;
        this.event = event;
        this.description = description;
        this.photo_Url = photoUrl;
        this.price = price;
        this.rating = rating;
        this.no_of_reviews = noOfReviews;
        this.tourism_type = tourismType;
    }
}
