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

    public Destination(String title, String location, String description, String photo_Url, Double price, Double rating, Integer no_of_reviews) {
        this.title = title;
        this.location = location;
        this.description = description;
        this.photo_Url = photo_Url;
        this.price = price;
        this.rating = rating;
        this.no_of_reviews = no_of_reviews;
    }
}
