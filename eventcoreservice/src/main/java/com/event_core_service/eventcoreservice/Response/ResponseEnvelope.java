package com.event_core_service.eventcoreservice.Response;

public class ResponseEnvelope<T> {
        private boolean success;
        private String message;
        private T data;

        public ResponseEnvelope(boolean success, String message, T data) {
            this.success = success;
            this.message = message;
            this.data = data;
        }

        public T getData() {
            return data;
        }
        public String getMessage(){
            return message;
        }
        public boolean isSuccess() {
            return success;
        }
    }


