package com.event_core_service.eventcoreservice.dto.request;


import com.event_core_service.eventcoreservice.enums.EventCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CreateEventRequestDTO {
    private String eventName;
    private String organizersName;
    private String organizersMobile;
    private String city;
    @Enumerated(EnumType.STRING)
    private EventCategory category;

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }
    public String getOrganizersName() { return organizersName; }
    public void setOrganizersName(String organizersName) { this.organizersName = organizersName; }
    public String getOrganizersMobile() { return organizersMobile; }
    public void setOrganizersMobile(String organizersMobile) { this.organizersMobile = organizersMobile; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public EventCategory getCategory() { return category; }
    public void setCategory(EventCategory category) { this.category = category; }
}
