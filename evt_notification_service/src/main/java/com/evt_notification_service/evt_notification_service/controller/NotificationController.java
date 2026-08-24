package com.evt_notification_service.evt_notification_service.controller;

import com.evt_notification_service.evt_notification_service.dto.EventNotificationResponse;
import com.evt_notification_service.evt_notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<EventNotificationResponse> getNotification(
            @RequestParam String entityId
    ) {

        return ResponseEntity.ok(
                notificationService.getNotification(entityId)
        );
    }
}