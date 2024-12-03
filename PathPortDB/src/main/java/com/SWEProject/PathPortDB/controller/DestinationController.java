package com.SWEProject.PathPortDB.controller;

import com.SWEProject.PathPortDB.model.Destination;
import com.SWEProject.PathPortDB.service.DestinationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {
    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    // Query 1: Get by ID
    @GetMapping("/{id}")
    public Destination getDestinationById(@PathVariable String id) {
        return destinationService.getDestinationById(id);
    }

    // Query 2: Get all destinations with selected fields
    @GetMapping
    public List<Destination> getAllDestinationsWithSelectedFields() {
        return destinationService.getAllDestinationsWithSelectedFields();
    }

    // Query 3: Get by category with selected fields
    @GetMapping("/category/{tourismType}")
    public List<Destination> getDestinationsByTourismType(@PathVariable String tourismType) {
        return destinationService.getDestinationsByCategoryWithSelectedFields(tourismType);
    }
}
