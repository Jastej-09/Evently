package com.evt_open_service.eventopenservice.dto.request;


import com.evt_open_service.eventopenservice.enums.EventCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEventRequestDTO {
    private String eventName;
    private String organizerName;
    private String organizerMobile;
    private String city;
    @Enumerated(EnumType.STRING)
    private EventCategory category;

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
}
