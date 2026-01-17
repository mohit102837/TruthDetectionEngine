package com.tbde.api.exception;

import java.time.LocalDateTime;

public class ApiError {

    private String message;
    private String detail;
    private LocalDateTime timestamp;

    public ApiError(String message, String detail) {
        this.message = message;
        this.detail = detail;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
