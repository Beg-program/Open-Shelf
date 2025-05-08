package com.openshelf.service;

import com.openshelf.model.User;
import com.openshelf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register a new user
    public User registerUser(String email, String password, String name) {
        // You can add additional validation like checking if the email already exists
        User newUser = new User(email, password, name);
        return userRepository.save(newUser);
    }

    // Get user by email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Hardcode an ADMIN user (for Day 2 task)
    public void createAdminUser() {
        if (userRepository.findByEmail("admin@openshelf.com") == null) {
            User admin = new User("admin@openshelf.com", "admin123", "Admin User");
            admin.setRole("ADMIN");
            userRepository.save(admin);
        }
    }
}
