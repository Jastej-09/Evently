package com.evt_bff.evtbff.dto.request;

import com.evt_bff.evtbff.enums.EventCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEventRequestDTO {

    @NotBlank(message = "Event name is required")
    private String eventName;

    @NotBlank(message = "Organizer name is required")
    private String organizerName;

    @NotBlank(message = "Organizer mobile is required")
    private String organizerMobile;

    @NotBlank(message = "City is required")
    private String city;

    @NotNull(message = "Category is required")
    private EventCategory category;
}