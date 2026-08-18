package com.event_core_service.eventcoreservice.dto.request;


import com.event_core_service.eventcoreservice.enums.EventCategory;
import com.event_core_service.eventcoreservice.enums.EventStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateEventRequest {
    private String eventName;
    private String organizersName;
    private String organizersMobile;
    private String city;
    @Enumerated(EnumType.STRING)
    private EventCategory category;
    @Enumerated(EnumType.STRING)
    private EventStatus status;
}

