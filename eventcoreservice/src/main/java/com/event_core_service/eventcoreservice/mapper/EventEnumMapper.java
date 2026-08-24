package com.event_core_service.eventcoreservice.mapper;

import com.event_core_service.eventcoreservice.enums.EventCategory;
import com.event_core_service.eventcoreservice.enums.EventStatus;

import static com.event_core_service.eventcoreservice.enums.EventStatus.*;
import static com.event_core_service.eventcoreservice.enums.EventCategory.*;
import static com.evently.grpc.event.EventCategory.EVENT_CATEGORY_UNSPECIFIED;
import static com.evently.grpc.event.EventCategory.UNRECOGNIZED;

public final class EventEnumMapper {

    private EventEnumMapper() {
    }

    public static   EventCategory toJpaCategory(
            com.evently.grpc.event.EventCategory category) {

        return switch (category) {

            case MUSIC -> EventCategory.MUSIC;

            case SPORTS -> EventCategory.SPORTS;

            case COMEDY -> EventCategory.COMEDY;

            case WORKSHOP -> EventCategory.WORKSHOP;

            case OTHER -> EventCategory.OTHER;

            case EVENT_CATEGORY_UNSPECIFIED ->
                    throw new IllegalArgumentException(
                            "Event category is required"
                    );

            case UNRECOGNIZED ->
                    throw new IllegalArgumentException(
                            "Unrecognized event category"
                    );
        };
    }


    public static com.evently.grpc.event.EventCategory toProtoCategory(
            EventCategory category) {

        return switch (category) {

            case MUSIC ->
                    com.evently.grpc.event.EventCategory.MUSIC;

            case SPORTS ->
                    com.evently.grpc.event.EventCategory.SPORTS;

            case COMEDY ->
                    com.evently.grpc.event.EventCategory.COMEDY;

            case WORKSHOP ->
                    com.evently.grpc.event.EventCategory.WORKSHOP;

            case OTHER ->
                    com.evently.grpc.event.EventCategory.OTHER;
        };
    }


    public static EventStatus toJpaStatus(
            com.evently.grpc.event.EventStatus status) {

        return switch (status) {

            case DRAFT -> EventStatus.DRAFT;

            case PUBLISHED -> EventStatus.PUBLISHED;

            case CANCELLED -> EventStatus.CANCELLED;

            case SOLD_OUT -> EventStatus.SOLD_OUT;

            case EVENT_STATUS_UNSPECIFIED ->
                    throw new IllegalArgumentException(
                            "Event status is required"
                    );

            case UNRECOGNIZED ->
                    throw new IllegalArgumentException(
                            "Unrecognized event status"
                    );
        };
    }


    public static com.evently.grpc.event.EventStatus toProtoStatus(
            EventStatus status) {

        return switch (status) {

            case DRAFT ->
                    com.evently.grpc.event.EventStatus.DRAFT;

            case PUBLISHED ->
                    com.evently.grpc.event.EventStatus.PUBLISHED;

            case CANCELLED ->
                    com.evently.grpc.event.EventStatus.CANCELLED;

            case SOLD_OUT ->
                    com.evently.grpc.event.EventStatus.SOLD_OUT;
        };
    }
}