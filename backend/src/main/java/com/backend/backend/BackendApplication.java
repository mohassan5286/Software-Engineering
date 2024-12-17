package com.backend.backend;

import com.backend.backend.Entity.Booking;
import com.backend.backend.Entity.Destination;
import com.backend.backend.Entity.NormalUser;
import com.backend.backend.Repository.BookingRepository;
import com.backend.backend.Repository.DestinationRepository;
import com.backend.backend.Repository.NormalUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.backend.backend.Repository")
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(BookingRepository bookingRepository, NormalUserRepository normalUserRepository) {
		return args -> {
			// Create and save a Booking object
			Booking booking = new Booking();
			booking.setPid("P123");
			booking.setUid("U456");
			booking.setBookingDate(new Date());  // Adjust the Date as necessary
			booking.setStatus("Confirmed");
			booking.setNo_of_persons(2);

			bookingRepository.save(booking);

			// Create a NormalUser with a booking history
			NormalUser normalUser = new NormalUser("John Doe", "johndoe@example.com", "U456", Arrays.asList(booking));

			normalUserRepository.save(normalUser);
		};
	}

}
