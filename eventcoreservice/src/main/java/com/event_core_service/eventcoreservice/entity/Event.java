package com.event_core_service.eventcoreservice.entity;
import com.event_core_service.eventcoreservice.enums.EventCategory;
import com.event_core_service.eventcoreservice.enums.EventStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Event extends BaseEntity {
    private String eventName;

    private String organizersName;

    @Column(unique = true)
    private String organizerMobile;

    private String city;

    @Enumerated(EnumType.STRING)
    private EventCategory category;

    @Enumerated(EnumType.STRING)
    private EventStatus status;



}
