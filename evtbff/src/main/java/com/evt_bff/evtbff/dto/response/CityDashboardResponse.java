package com.evt_bff.evtbff.dto.response;

import java.util.Map;

public record CityDashboardResponse(
        String city,
        Long totalEvents,
        Map<String, Long> eventsByCategory
) {
}