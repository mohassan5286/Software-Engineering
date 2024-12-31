package com.backend.backend.Repository;

import com.backend.backend.Entity.Booking;
import com.backend.backend.Entity.BookingId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface BookingRepository extends MongoRepository<Booking, BookingId> {
//    @Query("{'pid': ?0, 'uid': ?1}")
//    Optional<Booking> findByPidAndUid(String pid, String uid);
}
