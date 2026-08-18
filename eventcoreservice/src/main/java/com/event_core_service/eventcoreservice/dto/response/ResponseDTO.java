package com.event_core_service.eventcoreservice.dto.response;

import com.event_core_service.eventcoreservice.enums.EventCategory;
import com.event_core_service.eventcoreservice.enums.EventStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
public class ResponseDTO {
    private UUID id;
    private String eventName;
    private String organizerName;
    private String organizerMobile;
    private String city;
    @Enumerated(EnumType.STRING)
    private EventCategory category;
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
    }

