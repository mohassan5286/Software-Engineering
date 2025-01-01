package com.backend.backend.Service;

import com.backend.backend.Entity.Admin;
import com.backend.backend.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;


    public AdminService() {


    }


//    // Method to register a new user
//    public String registerUser(Admin Admin) {
//        // Check if user already exists by username
//        if (adminRepository.findByEmail(Admin.getEmail()) != null) {
//            return "email already taken!";
//        }
//
//
//
//
//        return "User registered successfully!";
//    }
//
//    // Method to get user by username (for login)
//    public Admin getUserByUsername(String adminname) {
//        return adminRepository.findByUsername(adminname);
//    }

    public Admin findByEmailAndPassword(String email, String password) {
        System.out.println("user "+ adminRepository.findByEmailAndPassword(email, password));
        return   adminRepository.findByEmailAndPassword(email, password);
    }
}
