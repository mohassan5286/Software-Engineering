package com.backend.backend.Repository;


import com.backend.backend.Entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends MongoRepository<Admin, String> {

    // Custom query to find a user by username
//    @Query("{'username': ?0}")
//    Admin findByUsername(String username);
//
//    // Custom query to find a user by verification token
//    @Query("{'verificationToken': ?0}")
//    Admin findByVerificationToken(String token);
//
//    // Custom query to find a user by email
//    @Query("{'email': ?0}")
//    Admin findByEmail(String email);

    // Custom query to find a user by username and password
    @Query("{'email': ?0, 'password': ?1}")
    Admin findByEmailAndPassword(String email, String password);

    // Additional custom query to find a user by email and check if they are enabled
//    @Query("{'email': ?0, 'enabled': true}")
//    Admin findByEmailAndEnabled(String email);
}
