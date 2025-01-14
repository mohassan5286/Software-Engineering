package com.backend.backend.Controller;

import com.backend.backend.Entity.Admin;
import com.backend.backend.Entity.User;
import com.backend.backend.Repository.UserRepository;
import com.backend.backend.Service.AdminService;
import com.backend.backend.Service.EmailService;
import com.backend.backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "http://localhost:3000/")
@RequestMapping("/api/auth")
public class AdminController {

    @Autowired
    private AdminService adminService;

//    @Autowired
//    private EmailService emailService;
//
//    @Autowired
//    private UserRepository userRepository;

    // Sign-up endpoint
//    @PostMapping("/signup")
//    public ResponseEntity<String> registerUser(@RequestBody User user) {
//        // Register the user in the database
//        System.out.println("aaaa");
//        String result = userService.registerUser(user);
//
//        if (result.equals("User registered successfully!")) {
//            // After registering the user, send a verification email
//            emailService.sendVerificationEmail(user);
//
//
//            return ResponseEntity.ok("User registered successfully! Please verify your email.");
//        }
//        return ResponseEntity.status(400).body(result);  // In case of failure
//    }

    @GetMapping("/Login/Admin/{email}/{password}")
    public String Login(@PathVariable String email, @PathVariable String password) {
        // Register the user in the database
        System.out.println("vdfdf");
        System.out.println("llll");
        System.out.println(password);
        System.out.println(email);

        Admin admin = adminService.findByEmailAndPassword(email, password);
        System.out.println(admin);
        return admin==null?null:admin.getId();
    }



    // Email verification endpoint
//    @GetMapping("/verify-email")
//    public ResponseEntity<String> verifyEmail(@RequestParam String token) {
//        // Find user by token
//        System.out.println("55555");
//        User user = userRepository.findByVerificationToken(token);
//
//        if (user == null) {
//            return ResponseEntity.status(400).body("Invalid token.");
//        }
//
//
//        user.setEnabled(true);
//        user.setVerificationToken(null);
//        userRepository.save(user);
//
//        return ResponseEntity.ok("Email verified successfully! You can now log in.");
//    }
}