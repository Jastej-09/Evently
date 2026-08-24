package com.evt_open_service.eventopenservice.dto.response;

import com.evently.grpc.event.EventCategory;
import com.evently.grpc.event.EventStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ResponseDTO {
    private UUID id;
    private String eventName;
    private String organizerName;
    private String organizerMobile;
    private String city;
    private EventCategory category;
    private EventStatus status;

    private LocalDateTime createdOn;
    private LocalDateTime modifiedOn;
//
//    public UUID getId() { return id; }
//    public void setId(UUID id) { this.id = id; }
//    public String getEventName() { return eventName; }
//    public void setEventName(String eventName) { this.eventName = eventName; }
//    public String getOrganizerName() { return organizerName; }
//    public void setOrganizerName(String organizerName) { this.organizerName = organizerName; }
//    public String getOrganizerMobile() { return organizerMobile; }
//    public void setOrganizerMobile(String organizerMobile) { this.organizerMobile = organizerMobile; }
//    public String getCity() { return city; }
//    public void setCity(String city) { this.city = city; }
//    public EventCategory getCategory() { return category; }
//    public void setCategory(EventCategory category) { this.category = category; }
//    public EventStatus getStatus() { return status; }
//    public void setStatus(EventStatus status) { this.status = status; }
//    public LocalDateTime getCreatedOn() { return createdOn; }
//    public void setCreatedOn(LocalDateTime createdOn) { this.createdOn = createdOn; }
//    public LocalDateTime getModifiedOn() { return modifiedOn; }
//    public void setModifiedOn(LocalDateTime modifiedOn) { this.modifiedOn = modifiedOn; }
}

