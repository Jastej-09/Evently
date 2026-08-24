package com.event_core_service.eventcoreservice.dto.response;

import com.event_core_service.eventcoreservice.enums.EventCategory;
import com.event_core_service.eventcoreservice.enums.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class EventStatusCountDTO {
    private EventStatus eventStatus;
    private Long count;

    public EventStatus getEventStatus() { return eventStatus; }
    public Long getCount() { return count; }
}
