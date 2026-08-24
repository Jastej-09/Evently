package com.evt_notification_service.evt_notification_service.dto;

import java.util.Map;

public record CityDashboardResponse(
        String city,
        Long totalEvents,
        Map<String, Long> eventsByCategory
) {
}