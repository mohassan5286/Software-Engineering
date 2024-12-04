package com.SWEProject.PathPortDB.service;

import com.SWEProject.PathPortDB.model.Destination;
import com.SWEProject.PathPortDB.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DestinationService {

    @Autowired
    private DestinationRepository destinationRepository;

    // Query 1: Get all fields of a document by ID
    public Destination getDestinationById(String id) {
        return destinationRepository.findDestinationById(id); // Matches repository method name
    }

    // Query 2: Get all documents with selected fields
    public List<Destination> getAllDestinationsWithSelectedFields() {
        return destinationRepository.findAllWithSpecificFields(); // Matches repository method name
    }

    // Query 3: Get all documents of a specific category with selected fields
    public List<Destination> getDestinationsByCategoryWithSelectedFields(String tourismType) {
        return destinationRepository.findByTourismTypeWithSpecificFields(tourismType); // Matches repository method name
    }
}
