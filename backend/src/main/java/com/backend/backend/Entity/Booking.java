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

@Setter
@Getter
@Document(collation = "booking")
@AllArgsConstructor
@NoArgsConstructor
@CompoundIndex(def = "{'pid': 1, 'uid': 1}", unique = true)
public class Booking {
//    @Id
    String pid;
//    @Indexed(unique = true)
//    @Indexed(unique = true)
    String uid;
    Date bookingDate;
    String status;
    int no_of_persons;
}

