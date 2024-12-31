package com.backend.backend.Controller;

import com.backend.backend.Entity.Destination;
import com.backend.backend.Repository.DestinationRepository;
import com.backend.backend.Service.DestinationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/destination")
@CrossOrigin(value = "http://localhost:3000/")
//@AllArgsConstructor
public class DestinationController {
    private DestinationService destinationService;
//    @Autowired
//    public DestinationController(destinationService){
//        this.destinationService = destinationService;
//    }
@Autowired
public DestinationController(DestinationService destinationService) {
    this.destinationService = destinationService;
}
    @GetMapping("/get/{pid}")
    public Destination fetchDestinationById(@PathVariable String pid) {
        return destinationService.getDestinationById(pid);
    }

    @GetMapping("/get/all")
    public List<Destination> fetchDestinationAll() {
        return destinationService.getDestinationAll();
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
    @GetMapping("/category/{tourism_type}")
    public List<Destination> getDestinationsByTourism_type(@PathVariable String tourism_type) {
        return destinationService.getDestinationsByCategoryWithSelectedFields(tourism_type);
    }
    @GetMapping("/search")
    public List<Destination> searchByTitle(@RequestParam String title) {
        return destinationService.searchByTitle(title);
    }
    
    @GetMapping("/filter")
    public List<Destination> filterDestinations(
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String tourism_type,
            @RequestParam(required = false) Double maxPrice) {

        return destinationService.filterDestinations(location, tourism_type, maxPrice);
    }

    @PostMapping("/add")
    public ResponseEntity<Destination> addDestination(@RequestBody Destination destination) {
        Destination savedDestination = destinationService.saveDestination(destination);
        return ResponseEntity.ok(savedDestination);
    }
}
