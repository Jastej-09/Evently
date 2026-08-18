package com.event_core_service.eventcoreservice.Exception;

public class IllegalStatusTransitionException extends RuntimeException {
    public IllegalStatusTransitionException(String message) {
        super(message);
    }
}
