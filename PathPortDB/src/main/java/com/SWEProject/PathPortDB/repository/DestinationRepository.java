package com.SWEProject.PathPortDB.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import com.SWEProject.PathPortDB.model.Destination;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends MongoRepository<Destination, String> {
    
    @Query("{ '_id': ?0 }")
    Destination findDestinationById(String id);

    @Query(
        value = "{}",
        fields = "{'title': 1, 'photoUrl': 1, 'price': 1, 'location': 1, 'rating': 1, 'description': 1}"
    )
    List<Destination> findAllWithSpecificFields();

    @Query(
        value = "{ 'tourism_type': { $in: [?0] } }",
        fields = "{'title': 1, 'photo_Url': 1, 'price': 1, 'location': 1, 'rating': 1, 'description': 1}"
    )
    List<Destination> findByTourismTypeWithSpecificFields(String tourismType);
}

