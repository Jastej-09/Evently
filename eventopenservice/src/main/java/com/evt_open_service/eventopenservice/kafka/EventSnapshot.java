package com.evt_open_service.eventopenservice.kafka;


public record EventSnapshot(
        String eventName,
        String city,
        String category
) {
}