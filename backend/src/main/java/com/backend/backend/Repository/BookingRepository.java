package com.backend.backend.Repository;

import com.backend.backend.Entity.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface BookingRepository extends MongoRepository<Booking, String> {

//    @Query
//    Optional<Booking> findBookingByUid();
}
