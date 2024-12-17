package com.backend.backend;

import com.backend.backend.Entity.Destination;
import com.backend.backend.Repository.DestinationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

//	@Bean
//	CommandLineRunner runner(DestinationRepository destinationRepository) {
//		return args -> {
//			Destination destination = new Destination(
//				"Sunny Beach Escape",
//				"Malibu, California",
//				"A beautiful beach destination with golden sands and vibrant sunsets.",
//				"https://example.com/images/sunny_beach_escape.jpg",
//				150.0,
//				 4.5,
//				120
//			);
//
//			destinationRepository.save(destination);
//		};
//	}
}
