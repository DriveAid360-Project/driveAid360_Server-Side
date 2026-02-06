package com.driveaid360.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

/**
 * Security configuration for DriveAid360 Server
 * Configures JWT-based authentication and authorization
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // CSRF disabled for stateless JWT-based API
            // Justification: This is a REST API using JWT tokens in Authorization headers.
            // CSRF protection is not needed for stateless APIs that don't use cookies for auth.
            // See: https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html#csrf-when
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .sessionManagement(session -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers(
                    "/health",
                    "/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/h2-console/**",
                    "/actuator/health"
                ).permitAll()
                // All other endpoints require authentication (will be configured later)
                .anyRequest().permitAll() // Temporarily permit all for initial setup
            );

        // Allow H2 console frame
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // TODO: In production, replace with specific allowed origins
        // Example: configuration.setAllowedOrigins(List.of("https://driveaid360.com", "https://app.driveaid360.com"));
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        // TODO: In production, specify only required headers
        // Example: configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(Arrays.asList("Authorization", "Content-Type"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
