package com.evt_bff.evtbff.dto.common;

public record ErrorResponse(
        boolean success,
        ErrorData error
) {

    public record ErrorData(
            String code,
            String message
    ) {
    }
}