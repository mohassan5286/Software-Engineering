package com.backend.backend.Repository;

import com.backend.backend.Entity.Booking;
import com.backend.backend.Entity.BookingId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends MongoRepository<Booking, BookingId> {

    // Custom query to find a booking by user ID
    @Query("{'id.uid': ?0}")
    List<Booking> findByUid(String uid);

    // Custom query to find a booking by product ID and user ID
    @Query("{'id.pid': ?0, 'id.uid': ?1}")
    Optional<Booking> findByUidAndPid(String pid, String uid);

    // Custom query to find a booking by product ID
    @Query("{'id.pid': ?0}")
    List<Booking> findByPid(String pid);

    // Custom query to delete a booking by product ID and user ID
    @Query(value = "{'id.pid': ?0, 'id.uid': ?1}", delete = true)
    void deleteByUidAndPid(String pid, String uid);

}
