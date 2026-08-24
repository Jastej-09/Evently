package com.evt_notification_service.evt_notification_service.document;
import com.evt_notification_service.evt_notification_service.repository.EventNotificationRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;
@Getter
@Setter
@Document(collection = "city_dashboards")
    public class CityDashboard {

        String city;     // note: city IS the document id, not a generated one
        long totalEvents;
        long publishedEvents;
        Map<String, Long> eventsByCategory;
        Instant lastUpdatedAt;
    }
