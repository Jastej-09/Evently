package com.evt_bff.evtbff.dto.response;

import java.util.List;

public record PageResponse<T>(List<T> content, long totalElements, int totalPages, int page, int size) {
    public PageResponse{
    }
}
