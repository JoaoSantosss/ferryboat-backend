package com.ferryboat.app.exception.response;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorResponse {
	
    private final int statusCode;
    private final String errorCode;
    private final String message;
    private final String path;
    private final LocalDateTime timestamp;


}