package com.evt_open_service.eventopenservice.kafka;

public final class KafkaTopic {

    private KafkaTopic() {
    }

    public static final String EVENT_PUBLISHED = "event.published";

    public static final String EVENT_STATUS_CHANGED =
            "event.status.changed";
}