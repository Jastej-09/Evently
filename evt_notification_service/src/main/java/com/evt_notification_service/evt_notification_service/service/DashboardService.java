package com.evt_notification_service.evt_notification_service.service;

import com.evt_notification_service.evt_notification_service.document.CityDashboard;
import com.evt_notification_service.evt_notification_service.dto.CityDashboardResponse;
import com.evt_notification_service.evt_notification_service.kafka.EventSnapshot;
import com.evt_notification_service.evt_notification_service.kafka.KafkaMessage;
import com.evt_notification_service.evt_notification_service.repository.CityDashboardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class DashboardService {

    private final CityDashboardRepository cityDashboardRepository;

    public void updateDashboard(KafkaMessage kafkaMessage)
                                {
                                    System.out.println("Dashboard Updated");
        EventSnapshot snapshot = kafkaMessage.payload();
        String city = snapshot.city();
        String category = snapshot.category();

        CityDashboard dashboard = cityDashboardRepository
                .findById(city)
                .orElseGet(() -> {

                    CityDashboard newDashboard = new CityDashboard();

                    newDashboard.setCity(city);
                    newDashboard.setTotalEvents(0L);
                    newDashboard.setEventsByCategory(new HashMap<>());

                    return newDashboard;
                });

        /*
         * Increment total event count.
         */
        dashboard.setTotalEvents(
                dashboard.getTotalEvents() + 1
        );

        /*
         * Increment category count.
         */
        Map<String, Long> eventsByCategory =
                dashboard.getEventsByCategory();

        if (eventsByCategory == null) {
            eventsByCategory = new HashMap<>();
        }

        eventsByCategory.merge(
                category,
                1L,
                Long::sum
        );

        dashboard.setEventsByCategory(eventsByCategory);

        cityDashboardRepository.save(dashboard);

        log.info(
                "Dashboard updated successfully. city={}, category={}",
                city,
                category
        );
    }
    public CityDashboardResponse getDashboard(String city) {

        CityDashboard dashboard = cityDashboardRepository
                .findById(city)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Dashboard not found for city: " + city
                        )
                );

        return new CityDashboardResponse(
                dashboard.getId(),
                dashboard.getTotalEvents(),
                dashboard.getEventsByCategory()
        );
    }
}