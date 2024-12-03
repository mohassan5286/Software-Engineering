package com.backend.backend.Service;

import com.backend.backend.Entity.Destination;
import com.backend.backend.Repository.DestinationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class DestinationService {
    private DestinationRepository destinationRepository;

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
}
