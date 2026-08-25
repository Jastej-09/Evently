package com.event_core_service.eventcoreservice.service;

import com.event_core_service.eventcoreservice.Exception.EventAlreadyExistsException;
import com.event_core_service.eventcoreservice.Exception.EventNotFoundException;
import com.event_core_service.eventcoreservice.Exception.IllegalStatusTransitionException;
import com.event_core_service.eventcoreservice.Response.ResponseEnvelope;
import com.event_core_service.eventcoreservice.dto.request.CreateEventRequestDTO;
import com.event_core_service.eventcoreservice.dto.response.*;
import com.event_core_service.eventcoreservice.entity.Event;

import com.event_core_service.eventcoreservice.enums.EventCategory;
import com.event_core_service.eventcoreservice.mapper.ResponseMapper;
import com.event_core_service.eventcoreservice.repository.EventRepository;
import com.event_core_service.eventcoreservice.enums.EventStatus;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventService {
    private EventRepository eventRepository;
    private ResponseMapper responseMapper;
    public EventService(EventRepository eventRepository,ResponseMapper responseMapper) {
        this.eventRepository = eventRepository;
        this.responseMapper = responseMapper;
    }

    public ResponseDTO getEvent( UUID id) {

        Event event = eventRepository.findById(id)
            .orElseThrow(() -> new EventNotFoundException("Event not found with id: " + id));
        return responseMapper.ResponseMapper(event);
//        return new ResponseEnvelope<>(true, "Event Found With ID : " + id, response);
    }

    public ResponseDTO createEvent(CreateEventRequestDTO request) throws EventAlreadyExistsException {
        if (eventRepository.existsByOrganizerMobile(request.getOrganizersMobile())) {
            throw new EventAlreadyExistsException("Event already exists");
        }

        Event event = new Event();
        event.setEventName(request.getEventName());
        event.setOrganizerName(request.getOrganizersName());
        event.setCity(request.getCity());
        event.setCategory(request.getCategory());
        event.setOrganizerMobile(request.getOrganizersMobile());
        event.setStatus(EventStatus.DRAFT);

         eventRepository.save(event);
         ResponseDTO response = responseMapper.ResponseMapper(event);
            return response;

//        return new ResponseEnvelope<>(true,"Event Created Successfully",response);


    }
    public ResponseDTO updateEventStatus(UUID id, EventStatus newStatus) {

        Event event = eventRepository.findById(id)
                .orElseThrow(() ->
                        new EventNotFoundException("Event not found with id: " + id)
                );

        EventStatus currentStatus = event.getStatus();

        boolean validTransition =
                (currentStatus == EventStatus.DRAFT &&
                        newStatus == EventStatus.PUBLISHED)

                        || (currentStatus == EventStatus.PUBLISHED &&
                        newStatus == EventStatus.CANCELLED)

                        || (currentStatus == EventStatus.PUBLISHED &&
                        newStatus == EventStatus.SOLD_OUT);

        if (!validTransition) {
            throw new IllegalStatusTransitionException(
                    "Cannot change status from "
                            + currentStatus
                            + " to "
                            + newStatus
            );
        }

        event.setStatus(newStatus);

         eventRepository.save(event);

         ResponseDTO response = new ResponseDTO();
         response.setId(event.getId());
         response.setEventName(event.getEventName());
         response.setStatus(event.getStatus());
         response.setCategory(event.getCategory());
         response.setOrganizerName(event.getOrganizerName());
         response.setOrganizerMobile(event.getOrganizerMobile());
         response.setCity(event.getCity());

         return response;
    }
    public Page<ResponseDTO> getFilteredResult(
            String city,
            EventCategory category,
            EventStatus status,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Event> events;

        if (city != null && category != null && status != null) {
            events = eventRepository.findByCityAndCategoryAndStatus(city, category, status, pageable);
        } else if (city != null && category != null) {
            events = eventRepository.findByCityAndCategory(city, category, pageable);
        } else if (city != null && status != null) {
            events = eventRepository.findByCityAndStatus(city, status, pageable);
        } else if (category != null && status != null) {
            events = eventRepository.findByCategoryAndStatus(category, status, pageable);
        } else if (city != null) {
            events = eventRepository.findByCity(city, pageable);
        } else if (category != null) {
            events = eventRepository.findByCategory(category, pageable);
        } else if (status != null) {
            events = eventRepository.findByStatus(status, pageable);
        } else {
            events = eventRepository.findAll(pageable);
        }

        Page<ResponseDTO> response = events.map(
                event -> responseMapper.ResponseMapper(event)
        );
        return response;
//        return new ResponseEnvelope<>(
//                true,
//                "Events fetched successfully",
//                response
//        );
    }
    public StatsResponseDTO getStats(){
        long totalEvent =  eventRepository.count();
        Map<EventStatus, Long> byStatus = eventRepository.countGroupByStatus()
                .stream()
                .collect(Collectors.toMap(EventStatusCountDTO :: getEventStatus,
                        EventStatusCountDTO :: getCount ));
        Map<EventCategory, Long> byCategory = eventRepository.countGroupByCategory()
                .stream()
                .collect(Collectors.toMap(EventCategoryCountDTO :: getEventcategory,
                        EventCategoryCountDTO :: getCount ));
        return new StatsResponseDTO(totalEvent,byStatus,byCategory);
    }
}
