
package com.backend.backend;

import com.backend.backend.Entity.Admin;
import com.backend.backend.Entity.Booking;
import com.backend.backend.Entity.Destination;
import com.backend.backend.Entity.User;
import com.backend.backend.Repository.AdminRepository;
import com.backend.backend.Repository.BookingRepository;
import com.backend.backend.Repository.DestinationRepository;
import com.backend.backend.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserRepository UserRepository, BookingRepository bookingRepository, AdminRepository adminRepository) {
		return args -> {
//			Admin admin2 = new Admin();
//			admin2.setId("2");
//			admin2.setUsername("adminUser2");
//			admin2.setPassword("securePassword2");
//			admin2.setEmail("admin2@example.com");
//
//			// Insert data into MongoDB
//			adminRepository.save(admin2);
//			Booking booking = new Booking(
//					"674f56cca02d3de66bcaebcc",
//					"6773e4cbc507775e318a753a",
//					new Date(),
//					2
//			);
//
//			bookingRepository.insert(booking);
//
//			User User = new User(
//					"name",
//					"ok",
//					"U123",
//					new ArrayList<>()
//			);
//
//			UserRepository.insert(User);
		};
	}
}
