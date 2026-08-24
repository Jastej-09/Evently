package com.evt_open_service.eventopenservice.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public record PageResponse<T>(List<T> content, long totalElements, int totalPages, int page, int size) {
    public PageResponse{
        if(content == null){
            content =List.of();
        }
    }

}
