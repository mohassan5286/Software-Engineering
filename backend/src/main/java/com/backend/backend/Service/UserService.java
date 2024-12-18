package com.backend.backend.Service;

import com.backend.backend.Entity.User;
import com.backend.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public UserService() {


    }


    // Method to register a new user
    public String registerUser(User user) {
        // Check if user already exists by username
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "email already taken!";
        }




        return "User registered successfully!";
    }

    // Method to get user by username (for login)
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmailAndPassword(String email, String password) {
        System.out.println("user "+ userRepository.findByEmailAndPassword(email, password));
          return   userRepository.findByEmailAndPassword(email, password);
    }
}
