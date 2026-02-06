package com.driveaid360.server.controller;

import com.driveaid360.server.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Health check and system status controller
 */
@RestController
@RequestMapping("/health")
@Tag(name = "Health", description = "System health and status endpoints")
public class HealthController {

    @GetMapping
    @Operation(summary = "Check system health", description = "Returns the health status of the DriveAid360 server")
    public ResponseEntity<ApiResponse<Map<String, Object>>> health() {
        Map<String, Object> healthData = new HashMap<>();
        healthData.put("status", "UP");
        healthData.put("timestamp", LocalDateTime.now());
        healthData.put("service", "DriveAid360 Server");
        healthData.put("version", "1.0.0");
        
        return ResponseEntity.ok(ApiResponse.success("System is healthy", healthData));
    }
}
