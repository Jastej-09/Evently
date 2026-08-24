package com.evt_open_service.eventopenservice.dto.response;

import com.evt_open_service.eventopenservice.enums.EventCategory;
import com.evt_open_service.eventopenservice.enums.EventStatus;
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
