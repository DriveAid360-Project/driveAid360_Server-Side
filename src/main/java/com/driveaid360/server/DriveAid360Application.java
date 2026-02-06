package com.driveaid360.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for DriveAid360 Server
 * 
 * DriveAid360 provides comprehensive roadside assistance including:
 * - Breakdown assistance with nearby providers
 * - Live tracking and communication (chat/call)
 * - SOS emergency module with offline SMS fallback
 * - AI voice assistant and smart features
 * - Insurance claims and document management
 * - Maps/navigation with traffic and weather
 * - Payment processing (LankaQR/card)
 * - Community features and rewards
 * - Admin portal for system management
 */
@SpringBootApplication
public class DriveAid360Application {

    public static void main(String[] args) {
        SpringApplication.run(DriveAid360Application.class, args);
    }
}
