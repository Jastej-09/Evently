package com.event_core_service.eventcoreservice.dto.response;

import com.event_core_service.eventcoreservice.enums.EventStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class StatusResponseDTO {
    private UUID id;
    private String EventName;
    @Enumerated(EnumType.STRING)
    private EventStatus status;

}
