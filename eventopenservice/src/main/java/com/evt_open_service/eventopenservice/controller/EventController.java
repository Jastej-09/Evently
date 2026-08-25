package com.evt_open_service.eventopenservice.controller;

import com.evt_open_service.eventopenservice.dto.request.CreateEventRequestDTO;
import com.evt_open_service.eventopenservice.dto.response.*;
import com.evt_open_service.eventopenservice.enums.EventCategory;
import com.evt_open_service.eventopenservice.enums.EventStatus;
import com.evt_open_service.eventopenservice.service.EventClientService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/open/v1/events")
public class EventController {
    private final EventClientService eventService;

    public EventController(EventClientService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/{id}")
    public ResponseDTO getEvent(@PathVariable UUID id) {
        return  eventService.getEvent(id);
    }

    @PostMapping
    public ResponseDTO createEvent(@RequestBody CreateEventRequestDTO request) {
        return eventService.createEvent(request);
    }

    @PatchMapping("/{id}/status")
    public ResponseDTO updateEventStatus(@PathVariable UUID id,
                                                            @RequestParam EventStatus status) {
        return eventService.updateEventStatus(id, status);
    }

    @GetMapping
    public PageResponse<ResponseDTO> getFilteredResult(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) EventCategory category,
            @RequestParam(required = false) EventStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return  eventService.getFilteredResult(city, category, status, page, size);
    }

    @GetMapping("/stats")
    public StatsResponseDTO getStats() {
        return eventService.getStats();
    }
}
