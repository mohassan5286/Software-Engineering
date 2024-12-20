package com.backend.backend.Service;

import com.backend.backend.Entity.Destination;
import com.backend.backend.Repository.DestinationRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service

public class DestinationService {
    private DestinationRepository destinationRepository;
    @Autowired
    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }
    public Destination getDestinationById(String pid) {
        try {

            return destinationRepository.findById(pid).get();

        } catch (NoSuchElementException e) {

            return new Destination();

        }
    }

    public List<Destination> getDestinationAll() {
        return destinationRepository.findAll();
    }

    // Query 2: Get all documents with selected fields
    public List<Destination> getAllDestinationsWithSelectedFields() {
        return destinationRepository.findAllWithSpecificFields(); // Matches repository method name
    }

    // Query 3: Get all documents of a specific category with selected fields
    public List<Destination> getDestinationsByCategoryWithSelectedFields(String tourism_type) {
        return destinationRepository.findByTourism_typeWithSpecificFields(tourism_type); // Matches repository method name
    }
    public List<Destination> searchByTitle(String title) {
        return destinationRepository.findByTitleContainingIgnoreCase(title);
    }


    public List<Destination> filterDestinations(String location, String tourism_type, Double maxPrice) {
        if (location != null && tourism_type != null && maxPrice != null) {
            return destinationRepository.findByLocationAndTourism_typeAndPriceLessThanEqual(location, tourism_type, maxPrice);
        } else if (location != null && tourism_type != null) {
            return destinationRepository.findByLocationAndTourism_type(location, tourism_type);
        } else if (location != null && maxPrice != null) {
            return destinationRepository.findByLocationAndPriceLessThanEqual(location, maxPrice);
        } else if (tourism_type != null && maxPrice != null) {
            return destinationRepository.findByTourism_typeAndPriceLessThanEqual(tourism_type, maxPrice);
        } else if (location != null) {
            return destinationRepository.findByLocation(location);
        } else if (tourism_type != null) {
            return destinationRepository.findByTourism_type(tourism_type);
        } else if (maxPrice != null) {
            return destinationRepository.findByPriceLessThanEqual(maxPrice);
        } else {
            return destinationRepository.findAll();
        }
    }
}
