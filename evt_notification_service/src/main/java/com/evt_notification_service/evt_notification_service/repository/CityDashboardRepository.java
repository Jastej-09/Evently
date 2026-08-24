package com.evt_notification_service.evt_notification_service.repository;

import com.evt_notification_service.evt_notification_service.document.CityDashboard;
import com.evt_notification_service.evt_notification_service.document.EventNotification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface CityDashboardRepository
        extends MongoRepository<CityDashboard, String> {
    Optional<CityDashboard> findByCity(String city);
}