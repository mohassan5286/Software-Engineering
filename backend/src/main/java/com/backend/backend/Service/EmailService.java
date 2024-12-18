package com.backend.backend.Service;





import com.backend.backend.Entity.User;
import com.backend.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UserRepository userRepository;

    // Send email verification link
    public void sendVerificationEmail(User user) {
        String token = UUID.randomUUID().toString();  // Generate a unique token

        // Save token in the user entity (you'll need to add a field to store it in the database)
        user.setVerificationToken(token);
//        userRepository.save(user);

        String verificationUrl = "http://localhost:8081/api/auth/verify-email?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Email Verification");
        message.setText("Click the link to verify your email: " + verificationUrl);

        emailSender.send(message);  // Send the email
    }
}
