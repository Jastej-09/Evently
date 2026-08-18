package com.event_core_service.eventcoreservice.dto.response;

import com.event_core_service.eventcoreservice.enums.EventCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class EventCategoryCountDTO {
    private EventCategory eventcategory;
    private Long count;
}
