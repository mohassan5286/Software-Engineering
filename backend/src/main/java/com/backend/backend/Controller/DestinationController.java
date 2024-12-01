package com.backend.backend.Controller;

import com.backend.backend.Entity.Destination;
import com.backend.backend.Repository.DestinationRepository;
import com.backend.backend.Service.DestinationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/destination")
@AllArgsConstructor
public class DestinationController {
    private DestinationService destinationService;

    @GetMapping("/get/{pid}")
    public Destination fetchDestinationById(@PathVariable String pid) {
        return destinationService.getDestinationById(pid);
    }

    @GetMapping("/get/all")
    public List<Destination> fetchDestinationAll() {
        return destinationService.getDestinationAll();
    }

}
