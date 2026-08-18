package com.event_core_service.eventcoreservice.dto.request;


import com.event_core_service.eventcoreservice.enums.EventCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CreateEventRequest {
    private String eventName;
    private String organizersName;
    private String organizersMobile;
    private String city;
    @Enumerated(EnumType.STRING)
    private EventCategory category;
}
