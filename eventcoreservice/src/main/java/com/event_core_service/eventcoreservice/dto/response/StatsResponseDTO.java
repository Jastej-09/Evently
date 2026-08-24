package com.event_core_service.eventcoreservice.dto.response;

import com.event_core_service.eventcoreservice.enums.EventCategory;
import com.event_core_service.eventcoreservice.enums.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
    public class StatsResponseDTO {

        private long totalEvents;

        private Map<EventStatus, Long> byStatus;

        private Map<EventCategory, Long> byCategory;

        public long getTotalEvents() { return totalEvents; }
        public Map<EventStatus, Long> getByStatus() { return byStatus; }
        public Map<EventCategory, Long> getByCategory() { return byCategory; }


    }
