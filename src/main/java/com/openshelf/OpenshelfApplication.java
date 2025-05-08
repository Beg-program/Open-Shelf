package com.openshelf;

import com.openshelf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpenshelfApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(OpenshelfApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Create hardcoded admin user on startup
        userService.createAdminUser();
    }
}
