package com.openshelf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                // Admin-specific routes for books and audiobooks
                .antMatchers("/admin/**", "/api/admin/**").hasRole("ADMIN")
                // Public access to book and audiobook routes
                .antMatchers("/api/books/**", "/api/audiobooks/**").permitAll()
                // Any other requests require authentication
                .anyRequest().authenticated()
            .and()
            .formLogin() // Enable form-based login
                .permitAll()
            .and()
            .logout() // Allow logout
                .permitAll();
    }

    // Password encoder for password hashing
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Define the UserDetailsService for authentication
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager();
        // Set your database configurations for users
        return userDetailsService;
    }
}
