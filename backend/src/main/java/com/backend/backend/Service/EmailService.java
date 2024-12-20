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

    public void sendVerificationEmail(User user) {
        String token = UUID.randomUUID().toString();


        user.setVerificationToken(token);
        userRepository.save(user);

        String verificationUrl = "http://localhost:8081/api/auth/verify-email?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Email Verification");
        message.setText("Click the link to verify your email: " + verificationUrl);

        emailSender.send(message);
    }
}
