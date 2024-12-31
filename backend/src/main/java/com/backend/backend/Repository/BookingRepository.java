package com.backend.backend.Repository;

import com.backend.backend.Entity.Booking;
import com.backend.backend.Entity.BookingId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface BookingRepository extends MongoRepository<Booking, BookingId> {

    // Custom query to find a booking by user ID
    @Query("{'id.uid': ?0}")
    Optional<Booking> findByUid(String uid);

    // Custom query to find a booking by user ID and product ID
    @Query("{'id.uid': ?0, 'id.pid': ?1}")
    Optional<Booking> findByUidAndPid(String uid, String pid);

    // Custom query to find a booking by product ID
    @Query("{'id.pid': ?0}")
    Optional<Booking> findByPid(String pid);
}
