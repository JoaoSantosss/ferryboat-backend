package com.ferryboat.app.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ferryboat.app.exception.UserNotFoundException;
import com.ferryboat.app.exception.response.ErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class UserGlobalExceptionHandler {
	
	 @ExceptionHandler(UserNotFoundException.class)
	    public ResponseEntity<ErrorResponse> handleInstructorNotFound(UserNotFoundException ex, HttpServletRequest request) {
	        log.error(ex.getMessage());
	        
	        ErrorResponse errorResponse = new ErrorResponse(
	        		HttpStatus.NOT_FOUND.value(),
	                "USER_NOT_FOUND",
	                ex.getMessage(),
	                request.getRequestURI(),
	                LocalDateTime.now());	        
	        
	        return ResponseEntity
	                .status(HttpStatus.NOT_FOUND)
	                .body(errorResponse);
	    }

}
