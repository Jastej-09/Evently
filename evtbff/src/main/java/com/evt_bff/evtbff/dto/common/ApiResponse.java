package com.evt_bff.evtbff.dto.common;


public record ApiResponse<T>(
        boolean success,
        String message,
        T data
) {
}