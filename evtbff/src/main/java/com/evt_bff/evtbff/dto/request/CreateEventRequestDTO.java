package com.evt_bff.evtbff.dto.request;


import com.evt_bff.evtbff.enums.EventCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEventRequestDTO {
    private String eventName;
    private String organizerName;
    private String organizerMobile;
    private String city;
    @Enumerated(EnumType.STRING)
    private EventCategory category;
}
