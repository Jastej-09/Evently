package com.event_core_service.eventcoreservice.service;
import com.event_core_service.eventcoreservice.dto.request.CreateEventRequestDTO;
import com.event_core_service.eventcoreservice.dto.response.ResponseDTO;
import com.event_core_service.eventcoreservice.entity.Event;
import com.event_core_service.eventcoreservice.enums.EventCategory;
import com.event_core_service.eventcoreservice.mapper.ResponseMapper;
import com.event_core_service.eventcoreservice.repository.EventRepository;
import com.event_core_service.eventcoreservice.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.event_core_service.eventcoreservice.enums.EventStatus;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.event_core_service.eventcoreservice.Exception.EventAlreadyExistsException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import com.event_core_service.eventcoreservice.Exception.IllegalStatusTransitionException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;


@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ResponseMapper responseMapper;

    @InjectMocks
    private EventService eventService;

    @Test
    void createEvent_success() {

        CreateEventRequestDTO request = new CreateEventRequestDTO();

        request.setEventName("Coldplay Concert");
        request.setOrganizersName("Jay Events");
        request.setCity("Delhi");
        request.setCategory(EventCategory.MUSIC);
        request.setOrganizersMobile("9999999999");

        when(eventRepository.existsByOrganizerMobile("9999999999"))
                .thenReturn(false);

        ResponseDTO expectedResponse = new ResponseDTO();

        when(responseMapper.ResponseMapper(any(Event.class)))
                .thenReturn(expectedResponse);

        ResponseDTO actualResponse =
                eventService.createEvent(request);

        assertSame(expectedResponse, actualResponse);

        ArgumentCaptor<Event> eventCaptor =
                ArgumentCaptor.forClass(Event.class);

        verify(eventRepository).save(eventCaptor.capture());

        Event savedEvent = eventCaptor.getValue();

        assertEquals("Coldplay Concert", savedEvent.getEventName());
        assertEquals("Jay Events", savedEvent.getOrganizerName());
        assertEquals("Delhi", savedEvent.getCity());
        assertEquals(EventCategory.MUSIC, savedEvent.getCategory());
        assertEquals("9999999999", savedEvent.getOrganizerMobile());
        assertEquals(EventStatus.DRAFT, savedEvent.getStatus());

        verify(eventRepository)
                .existsByOrganizerMobile("9999999999");

        verify(responseMapper)
                .ResponseMapper(any(Event.class));
    }
    @Test
    void createEvent_duplicateMobile_throwsException() {

        CreateEventRequestDTO request = new CreateEventRequestDTO();

        request.setEventName("Coldplay Concert");
        request.setOrganizersName("Jay Events");
        request.setCity("Delhi");
        request.setCategory(EventCategory.MUSIC);
        request.setOrganizersMobile("9999999999");

        when(eventRepository.existsByOrganizerMobile("9999999999"))
                .thenReturn(true);

        assertThrows(
                EventAlreadyExistsException.class,
                () -> eventService.createEvent(request)
        );

        verify(eventRepository)
                .existsByOrganizerMobile("9999999999");

        verify(eventRepository, never())
                .save(any(Event.class));

        verify(responseMapper, never())
                .ResponseMapper(any(Event.class));
    }
    @Test
    void updateStatus_draftToPublished_success() {

        UUID eventId = UUID.randomUUID();

        Event event = new Event();
        event.setEventName("Coldplay Concert");
        event.setOrganizerName("Jay Events");
        event.setOrganizerMobile("9999999999");
        event.setCity("Delhi");
        event.setCategory(EventCategory.MUSIC);
        event.setStatus(EventStatus.DRAFT);

        when(eventRepository.findById(eventId))
                .thenReturn(Optional.of(event));

        ResponseDTO actualResponse =
                eventService.updateEventStatus(
                        eventId,
                        EventStatus.PUBLISHED
                );

        assertEquals(EventStatus.PUBLISHED, event.getStatus());

        assertEquals(event.getId(), actualResponse.getId());
        assertEquals(event.getEventName(), actualResponse.getEventName());
        assertEquals(event.getOrganizerName(), actualResponse.getOrganizerName());
        assertEquals(event.getOrganizerMobile(), actualResponse.getOrganizerMobile());
        assertEquals(event.getCity(), actualResponse.getCity());
        assertEquals(event.getCategory(), actualResponse.getCategory());
        assertEquals(EventStatus.PUBLISHED, actualResponse.getStatus());

        verify(eventRepository).findById(eventId);
        verify(eventRepository).save(event);
    }
    @Test
    void updateStatus_illegalTransition_throwsException() {

        UUID eventId = UUID.randomUUID();

        Event event = new Event();
        event.setEventName("Coldplay Concert");
        event.setOrganizerName("Jay Events");
        event.setOrganizerMobile("9999999999");
        event.setCity("Delhi");
        event.setCategory(EventCategory.MUSIC);
        event.setStatus(EventStatus.DRAFT);

        when(eventRepository.findById(eventId))
                .thenReturn(Optional.of(event));

        assertThrows(
                IllegalStatusTransitionException.class,
                () -> eventService.updateEventStatus(
                        eventId,
                        EventStatus.CANCELLED
                )
        );

        verify(eventRepository).findById(eventId);

        verify(eventRepository, never())
                .save(any(Event.class));

        assertEquals(EventStatus.DRAFT, event.getStatus());
    }
    @Test
    void updateStatus_publishedToCancelled_success() {

        UUID eventId = UUID.randomUUID();

        Event event = new Event();
        event.setEventName("Coldplay Concert");
        event.setOrganizerName("Jay Events");
        event.setOrganizerMobile("9999999999");
        event.setCity("Delhi");
        event.setCategory(EventCategory.MUSIC);
        event.setStatus(EventStatus.PUBLISHED);

        when(eventRepository.findById(eventId))
                .thenReturn(Optional.of(event));

        ResponseDTO actualResponse =
                eventService.updateEventStatus(
                        eventId,
                        EventStatus.CANCELLED
                );

        assertEquals(EventStatus.CANCELLED, event.getStatus());

        assertEquals(event.getEventName(), actualResponse.getEventName());
        assertEquals(event.getOrganizerName(), actualResponse.getOrganizerName());
        assertEquals(event.getOrganizerMobile(), actualResponse.getOrganizerMobile());
        assertEquals(event.getCity(), actualResponse.getCity());
        assertEquals(event.getCategory(), actualResponse.getCategory());
        assertEquals(EventStatus.CANCELLED, actualResponse.getStatus());

        verify(eventRepository).findById(eventId);
        verify(eventRepository).save(event);
    }
    @Test
    void updateStatus_publishedToSoldOut_success() {

        UUID eventId = UUID.randomUUID();

        Event event = new Event();
        event.setEventName("Coldplay Concert");
        event.setOrganizerName("Jay Events");
        event.setOrganizerMobile("9999999999");
        event.setCity("Delhi");
        event.setCategory(EventCategory.MUSIC);
        event.setStatus(EventStatus.PUBLISHED);

        when(eventRepository.findById(eventId))
                .thenReturn(Optional.of(event));

        ResponseDTO actualResponse =
                eventService.updateEventStatus(
                        eventId,
                        EventStatus.SOLD_OUT
                );

        assertEquals(EventStatus.SOLD_OUT, event.getStatus());

        assertEquals(event.getEventName(), actualResponse.getEventName());
        assertEquals(event.getOrganizerName(), actualResponse.getOrganizerName());
        assertEquals(event.getOrganizerMobile(), actualResponse.getOrganizerMobile());
        assertEquals(event.getCity(), actualResponse.getCity());
        assertEquals(event.getCategory(), actualResponse.getCategory());
        assertEquals(EventStatus.SOLD_OUT, actualResponse.getStatus());

        verify(eventRepository).findById(eventId);
        verify(eventRepository).save(event);
    }
}