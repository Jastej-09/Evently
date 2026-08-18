package com.event_core_service.eventcoreservice.controller;

import com.event_core_service.eventcoreservice.Response.ResponseEnvelope;
import com.event_core_service.eventcoreservice.dto.request.CreateEventRequest;
import com.event_core_service.eventcoreservice.dto.response.ResponseDTO;
import com.event_core_service.eventcoreservice.dto.response.StatsResponseDTO;
import com.event_core_service.eventcoreservice.dto.response.StatusResponseDTO;
import com.event_core_service.eventcoreservice.enums.EventCategory;
import com.event_core_service.eventcoreservice.enums.EventStatus;
import com.event_core_service.eventcoreservice.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping()
public class EventController {
    private EventService eventService;
    EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/v1/events/{id}")
     public ResponseEnvelope<ResponseDTO> getEvent(@PathVariable UUID id){
        return eventService.getEvent(id);
    }
    @PostMapping("/v1/events")
    public ResponseEnvelope<ResponseDTO> createEvent(@RequestBody CreateEventRequest evt){
        return eventService.createEvent(evt);
    }
    @PatchMapping("/v1/events/{id}/status")
    public ResponseEnvelope<StatusResponseDTO> updateStatus(
            @PathVariable UUID id,
            @RequestParam EventStatus status) {

        return eventService.updateEventStatus(id, status);

    }
    @GetMapping("/v1/events/stats")
    public ResponseEnvelope<StatsResponseDTO> getStats(){
        return eventService.getStats();
    }

    @GetMapping("/v1/events")
    public ResponseEnvelope<Page<ResponseDTO>> getFilteredResult(
            @RequestParam (required = false) String city,
            @RequestParam (required = false) EventCategory category,
            @RequestParam (required = false) EventStatus status,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size
    ){
        return eventService.getFilteredResult(city,category,status,page,size);
    }

}
