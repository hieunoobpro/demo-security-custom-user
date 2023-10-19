package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // Configure the mailSender properties
        mailSender.setHost("your-smtp-host");
        mailSender.setPort(587);
        mailSender.setUsername("your-email-username");
        mailSender.setPassword("your-email-password");
        return mailSender;
    }
}
