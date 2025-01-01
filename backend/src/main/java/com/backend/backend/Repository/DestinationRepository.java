package com.backend.backend.Repository;

import com.backend.backend.Entity.Destination;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DestinationRepository extends MongoRepository<Destination, String> {
    @Query("{ 'pid': ?0 }")
    Destination findDestinationById(String id);

    @Query("{}")
    List<Destination> findAllWithSpecificFields();

    @Query(value = "{ 'tourism_type': ?0 }", fields = "{'title': 1, 'photo_Url': 1, 'price': 1, 'location': 1, 'rating': 1, 'description': 1}")
    List<Destination> findByTourism_typeWithSpecificFields(String tourism_type);
    // 1. Find by title containing (case-insensitive)
    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    List<Destination> findByTitleContainingIgnoreCase(String title);

    // 2. Find by location, tourism_type, and price less than or equal
    @Query("{ 'location': ?0, 'tourism_type': ?1, 'price': { $lte: ?2 } }")
    List<Destination> findByLocationAndTourism_typeAndPriceLessThanEqual(String location, String tourism_type, Double maxPrice);

    // 3. Find by location and price less than or equal
    @Query("{ 'location': ?0, 'price': { $lte: ?1 } }")
    List<Destination> findByLocationAndPriceLessThanEqual(String location, Double maxPrice);

    // 4. Find by tourism_type and price less than or equal
    @Query("{ 'tourism_type': ?0, 'price': { $lte: ?1 } }")
    List<Destination> findByTourism_typeAndPriceLessThanEqual(String tourism_type, Double maxPrice);

    // 5. Find by location and tourism_type
    @Query("{ 'location': ?0, 'tourism_type': ?1 }")
    List<Destination> findByLocationAndTourism_type(String location, String tourism_type);

    // 6. Find by location
    @Query("{ 'location': ?0 }")
    List<Destination> findByLocation(String location);

    // 7. Find by tourism_type
    @Query("{ 'tourism_type': ?0 }")
    List<Destination> findByTourism_type(String tourism_type);

    // 8. Find by price less than or equal
    @Query("{ 'price': { $lte: ?0 } }")
    List<Destination> findByPriceLessThanEqual(Double maxPrice);

}
