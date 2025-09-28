package com.franco.insalud.web_insalud.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordGeneratorRunner implements CommandLineRunner {
    
    @Override
    public void run(String... args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "12345";
        
        String hash = encoder.encode(rawPassword);
        System.out.println("Password: " + rawPassword + " | BCrypt: " + hash);
    }

}