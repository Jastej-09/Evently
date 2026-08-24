package com.evt_notification_service.evt_notification_service.controller;

import com.evt_notification_service.evt_notification_service.dto.CityDashboardResponse;
import com.evt_notification_service.evt_notification_service.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/{city}")
    public ResponseEntity<CityDashboardResponse> getDashboard(
            @PathVariable String city
    ) {

        return ResponseEntity.ok(
                dashboardService.getDashboard(city)
        );
    }
}