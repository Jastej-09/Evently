package com.evt_bff.evtbff.client;

import com.evt_bff.evtbff.dto.request.CreateEventRequestDTO;
import com.evt_bff.evtbff.dto.response.ResponseDTO;
import com.evt_bff.evtbff.dto.response.StatsResponseDTO;
import com.evt_bff.evtbff.enums.EventCategory;
import com.evt_bff.evtbff.enums.EventStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import com.evt_bff.evtbff.dto.response.PageResponse;

@FeignClient(name = "event-open-service", url = "${evt.open-service.url:http://localhost:8081}")
public interface EventClient {
    @GetMapping("/open/v1/events/{id}")
    ResponseDTO getEvent(@PathVariable UUID id);
    @PostMapping("/open/v1/events")
    ResponseDTO createEvent(@RequestBody CreateEventRequestDTO createEventRequestDTO);
    @PatchMapping("/open/v1/events/{id}/status")
    ResponseDTO updateEventStatus(@PathVariable UUID id, @RequestParam EventStatus status);
    @GetMapping("/open/v1/events/stats")
    StatsResponseDTO getStats();
    @GetMapping("/open/v1/events")
    PageResponse<ResponseDTO> getFilteredResult(@RequestParam (required = false) String city,
                                          @RequestParam (required = false) EventCategory category,
                                          @RequestParam (required = false) EventStatus status,
                                          @RequestParam (defaultValue = "0") int page,
                                          @RequestParam (defaultValue = "10") int size);
}
