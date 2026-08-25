package com.evt_bff.evtbff.client;

import com.evt_bff.evtbff.dto.response.CityDashboardResponse;
import com.evt_bff.evtbff.dto.response.EventNotificationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(
        name = "event-notification-service",
        url = "${evt.notification-service.url:http://localhost:8083}"
)
public interface NotificationClient {

    @GetMapping("/v1/notifications")
    EventNotificationResponse getNotification(
            @RequestParam UUID entityId
    );
    @GetMapping("/v1/dashboard/{city}")
    CityDashboardResponse getDashboard(
            @PathVariable String city
    );
}
