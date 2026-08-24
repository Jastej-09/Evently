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

    private String organizerName;

    @Column(unique = true)
    private String organizerMobile;

    private String city;

    @Enumerated(EnumType.STRING)
    private EventCategory category;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }
    public String getOrganizerName() { return organizerName; }
    public void setOrganizerName(String organizerName) { this.organizerName = organizerName; }
    public String getOrganizerMobile() { return organizerMobile; }
    public void setOrganizerMobile(String organizerMobile) { this.organizerMobile = organizerMobile; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public EventCategory getCategory() { return category; }
    public void setCategory(EventCategory category) { this.category = category; }
    public EventStatus getStatus() { return status; }
    public void setStatus(EventStatus status) { this.status = status; }



}
