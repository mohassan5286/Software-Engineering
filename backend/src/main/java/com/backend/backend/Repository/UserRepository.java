package com.backend.backend.Repository;

import com.backend.backend.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // Custom query to find a user by username
    @Query("{'username': ?0}")
    User findByUsername(String username);

    // Custom query to find a user by verification token
    @Query("{'verificationToken': ?0}")
    User findByVerificationToken(String token);

    // Custom query to find a user by email
    @Query("{'email': ?0}")
    User findByEmail(String email);

    // Custom query to find a user by username and password
    @Query("{'email': ?0, 'password': ?1}")
    User findByEmailAndPassword(String email, String password);

    // Additional custom query to find a user by email and check if they are enabled
    @Query("{'email': ?0, 'enabled': true}")
    User findByEmailAndEnabled(String email);
}
