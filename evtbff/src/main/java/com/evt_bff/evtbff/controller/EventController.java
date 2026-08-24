package com.evt_bff.evtbff.controller;

import com.evt_bff.evtbff.client.EventClient;
import com.evt_bff.evtbff.client.NotificationClient;
import com.evt_bff.evtbff.dto.request.CreateEventRequestDTO;
import com.evt_bff.evtbff.dto.response.CityDashboardResponse;
import com.evt_bff.evtbff.dto.response.EventNotificationResponse;
import com.evt_bff.evtbff.dto.response.ResponseDTO;
import com.evt_bff.evtbff.enums.EventCategory;
import com.evt_bff.evtbff.enums.EventStatus;
import com.evt_bff.evtbff.responseenvelope.ResponseEnvelope;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequestMapping()
@RequiredArgsConstructor
public class EventController {
    private final EventClient eventClient;
    private final NotificationClient notificationClient;

    @GetMapping("api/v1/events/{id}")
    public ResponseDTO getEvent(@PathVariable UUID id) {
        return eventClient.getEvent(id);
    }

    @PostMapping("api/v1/events")
    public ResponseEnvelope<ResponseDTO> createEvent(@RequestBody CreateEventRequestDTO evt) {
        ResponseDTO responseDTO = eventClient.createEvent(evt);
        return new ResponseEnvelope<>(true, "Event created", responseDTO);
    }

    @PatchMapping("api/v1/events/{id}/status")
    public ResponseDTO updateStatus(
            @PathVariable UUID id,
            @RequestParam EventStatus status) {

        return eventClient.updateEventStatus(id, status);

    }

    @GetMapping("/v1/events/stats")
    public ResponseDTO getStats() {
        return eventClient.getStats();
    }


//    @GetMapping("/v1/events")
//    public Page<ResponseDTO>getFilteredResult(
//            @RequestParam (required = false) String city,
//            @RequestParam (required = false) EventCategory category,
//            @RequestParam (required = false) EventStatus status,
//            @RequestParam (defaultValue = "0") int page,
//            @RequestParam (defaultValue = "10") int size
//    ){
//        return eventClient.getFilteredResult(city,category,status,page,size);
//    }


    @GetMapping("/api/v1/notifications")
    public EventNotificationResponse getNotification(
            @RequestParam UUID entityId
    ) {
        return notificationClient.getNotification(entityId);
    }
    @GetMapping("api/v1/dashboard/{city}")
    public CityDashboardResponse getDashboard(
            @PathVariable String city
    ) {
        return notificationClient.getDashboard(city);
    }    }

