package com.evt_bff.evtbff.controller;

import com.evt_bff.evtbff.client.EventClient;
import com.evt_bff.evtbff.client.NotificationClient;
import com.evt_bff.evtbff.dto.request.CreateEventRequestDTO;
import com.evt_bff.evtbff.dto.response.CityDashboardResponse;
import com.evt_bff.evtbff.dto.response.EventNotificationResponse;
import com.evt_bff.evtbff.dto.response.ResponseDTO;
import com.evt_bff.evtbff.dto.response.StatsResponseDTO;
import com.evt_bff.evtbff.enums.EventCategory;
import com.evt_bff.evtbff.enums.EventStatus;
import com.evt_bff.evtbff.responseenvelope.ResponseEnvelope;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.RequestEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequestMapping()
@RequiredArgsConstructor
public class EventController {
    private final EventClient eventClient;
    private final NotificationClient notificationClient;
    @GetMapping("api/v1/events/{id}")
    public ResponseEnvelope<ResponseDTO> getEvent(@PathVariable UUID id) {
        return new ResponseEnvelope<>(true, "Event found", eventClient.getEvent(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("api/v1/events")
    public ResponseEnvelope<ResponseDTO> createEvent(@Valid @RequestBody CreateEventRequestDTO evt) {
        ResponseDTO responseDTO = eventClient.createEvent(evt);
        return new ResponseEnvelope<>(true, "Event created", responseDTO);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("api/v1/events/{id}/status")
    public ResponseEnvelope<ResponseDTO> updateStatus(
            @PathVariable UUID id,
            @RequestParam EventStatus status) {

        return new ResponseEnvelope<>(true, "Status updated", eventClient.updateEventStatus(id, status));

    }
    @GetMapping("api/v1/events/stats")
    public ResponseEnvelope<StatsResponseDTO> getStats() {
        return new ResponseEnvelope<>(true, "Stats fetched", eventClient.getStats());
    }


    @GetMapping("api/v1/events")
    public ResponseEnvelope<com.evt_bff.evtbff.dto.response.PageResponse<ResponseDTO>> getFilteredResult(
            @RequestParam (required = false) String city,
            @RequestParam (required = false) EventCategory category,
            @RequestParam (required = false) EventStatus status,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "10") int size
    ){
        return new ResponseEnvelope<>(true, "Events fetched", eventClient.getFilteredResult(city,category,status,page,size));
    }

    @GetMapping("/api/v1/notifications")
    public ResponseEnvelope<EventNotificationResponse> getNotification(
            @RequestParam UUID entityId
    ) {
        return new ResponseEnvelope<>(true, "Notification found", notificationClient.getNotification(entityId));
    }

    @GetMapping("api/v1/dashboard/{city}")
    public ResponseEnvelope<CityDashboardResponse> getDashboard(
            @PathVariable String city
    ) {
        return new ResponseEnvelope<>(true, "Dashboard fetched", notificationClient.getDashboard(city));
    }
}

