package com.event_core_service.eventcoreservice.Exception;

import com.event_core_service.eventcoreservice.Response.ResponseEnvelope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EventAlreadyExistsException.class)
    public ResponseEntity<ResponseEnvelope<Void>>handleEventAlreadyExistsException(EventAlreadyExistsException ex) {
        ResponseEnvelope<Void> response = new ResponseEnvelope<>(false,ex.getMessage(),null);
        return  ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
