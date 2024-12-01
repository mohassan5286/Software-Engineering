package com.backend.backend.Repository;

import com.backend.backend.Entity.Destination;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DestinationRepository extends MongoRepository<Destination, String> {
}
