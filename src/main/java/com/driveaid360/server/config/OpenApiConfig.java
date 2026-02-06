package com.driveaid360.server.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * OpenAPI/Swagger configuration for API documentation
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI driveAid360OpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:8080/api/v1");
        devServer.setDescription("Development server");

        Contact contact = new Contact();
        contact.setName("DriveAid360 Team");
        contact.setEmail("support@driveaid360.com");

        License license = new License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT");

        Info info = new Info()
                .title("DriveAid360 Server API")
                .version("1.0.0")
                .description("Backend API for DriveAid360 roadside assistance platform. " +
                        "Provides endpoints for breakdown assistance, SOS emergency, provider management, " +
                        "live tracking, payments, insurance, and community features.")
                .contact(contact)
                .license(license);

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer));
    }
}
