package com.backend.backend.Repository;

import com.backend.backend.Entity.Destination;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DestinationRepository extends MongoRepository<Destination, String> {
    @Query("{ '_id': ?0 }")
    Destination findDestinationById(String id);

    @Query("{}")
    List<Destination> findAllWithSpecificFields();

    @Query(value = "{ 'tourism_type': ?0 }", fields = "{'title': 1, 'photoUrl': 1, 'price': 1, 'location': 1, 'rating': 1, 'description': 1}")
    List<Destination> findByTourismTypeWithSpecificFields(String tourismType);
}
