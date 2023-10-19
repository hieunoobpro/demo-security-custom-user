package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private final UserRepository userRepository;
    private JavaMailSender javaMailSender;

    public UserController(UserRepository userRepository, JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.javaMailSender = javaMailSender;
    }

    @GetMapping()
    public Optional<User> getUsersByName(@RequestParam("email") String email) {
        return userRepository.findByEmail(email);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        user.setIsEnabled(false);
        userRepository.save(user);
        sendVerificationEmail(user);
    }

    private void sendVerificationEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Email Verification");
        message.setText("Please verify your email by clicking the link: http://example.com/verify?userId=" + user.getId());
        javaMailSender.send(message);
    }
}
