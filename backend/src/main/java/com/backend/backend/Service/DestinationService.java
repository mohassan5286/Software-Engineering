package com.backend.backend.Service;

import com.backend.backend.Entity.Destination;
import com.backend.backend.Repository.DestinationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DestinationService {
    private DestinationRepository destinationRepository;

    public Destination getDestinationById(String pid) {
        return destinationRepository.findById(pid).get();
    }

    public List<Destination> getDestinationAll() {
        return destinationRepository.findAll();
    }
}
