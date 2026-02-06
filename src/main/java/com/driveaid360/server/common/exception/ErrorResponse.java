package com.driveaid360.server.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Standard error response model for all API errors
 */
@Getter
public class ErrorResponse {
    private final int status;
    private final String error;
    private final String message;
    private final long timestamp;
    private final String path;

    public ErrorResponse(HttpStatus status, String message, String path) {
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.timestamp = System.currentTimeMillis();
        this.path = path;
    }

    public ErrorResponse(int status, String error, String message, String path) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
        this.path = path;
    }
}
