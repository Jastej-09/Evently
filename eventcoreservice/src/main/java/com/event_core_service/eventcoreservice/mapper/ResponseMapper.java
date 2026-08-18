package com.event_core_service.eventcoreservice.mapper;

import com.event_core_service.eventcoreservice.dto.response.ResponseDTO;
import com.event_core_service.eventcoreservice.entity.Event;
import org.springframework.stereotype.Component;

@Component
public class ResponseMapper {
     public ResponseDTO ResponseMapper(Event event){
        ResponseDTO response = new ResponseDTO();
        response.setEventName(event.getEventName());
        response.setId(event.getId());
        response.setCategory(event.getCategory());
        response.setStatus(event.getStatus());
        response.setCity(event.getCity());
        response.setCreatedOn(event.getCreatedOn());
        response.setModifiedOn(event.getModifiedOn());
        response.setOrganizerName(event.getOrganizersName());
        response.setOrganizerMobile(event.getOrganizerMobile());
        return response;
    }
}
