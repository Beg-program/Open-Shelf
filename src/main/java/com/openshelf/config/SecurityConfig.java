package com.openshelf.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig {

    /**
     * Defines the security filter chain for HTTP security rules.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Optional: disable CSRF for APIs
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**", "/api/admin/**").hasRole("ADMIN")
                .requestMatchers("/api/books/**", "/api/audiobooks/**").permitAll()
                .requestMatchers("/api/users/register", "/api/auth/login").permitAll() // Public access to login & register
                .anyRequest().authenticated() // All other requests require authentication
            )
            .formLogin(form -> form.permitAll()) // ✅ Disable default login form for APIs
            .httpBasic().disable() // Disable HTTP Basic authentication
            .logout(logout -> logout.permitAll()); // Allow logout for all
        return http.build();
    }

    /**
     * Bean for encoding passwords using BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * UserDetailsService backed by JDBC.
     * Ensure a DataSource bean is defined elsewhere in your config.
     */
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }
}
