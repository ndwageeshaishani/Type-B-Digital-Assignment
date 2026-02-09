package com.wageesha.typebdigitalassignment.exception;

import com.wageesha.typebdigitalassignment.dto.GreetingResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidNameException.class)
    public ResponseEntity<GreetingResponseDTO> handleInvalidNameException(InvalidNameException ex) {
        GreetingResponseDTO response = GreetingResponseDTO.builder().error(ex.getMessage()).build();
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GreetingResponseDTO> handleRuntimeException(RuntimeException ex) {
        GreetingResponseDTO response = GreetingResponseDTO.builder().error(ex.getMessage()).build();
        return ResponseEntity.badRequest().body(response);
    }
}