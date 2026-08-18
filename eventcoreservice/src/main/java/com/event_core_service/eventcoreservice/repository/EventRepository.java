package com.event_core_service.eventcoreservice.repository;

import com.event_core_service.eventcoreservice.dto.response.EventCategoryCountDTO;
import com.event_core_service.eventcoreservice.dto.response.EventStatusCountDTO;
import com.event_core_service.eventcoreservice.entity.Event;
import com.event_core_service.eventcoreservice.enums.EventCategory;
import com.event_core_service.eventcoreservice.enums.EventStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    public boolean existsByOrganizerMobile(String organizerMobile);
       public Page<Event> findAll(Pageable pageable);

        Page<Event> findByCity(String city, Pageable pageable);

        Page<Event> findByCategory(EventCategory category, Pageable pageable);

        Page<Event> findByStatus(EventStatus status, Pageable pageable);

        Page<Event> findByCityAndCategory(
                String city,
                EventCategory category,
                Pageable pageable
        );

        Page<Event> findByCityAndStatus(
                String city,
                EventStatus status,
                Pageable pageable
        );

        Page<Event> findByCategoryAndStatus(
                EventCategory category,
                EventStatus status,
                Pageable pageable
        );

      public Page<Event> findByCityAndCategoryAndStatus(
                String city,
                EventCategory category,
                EventStatus status,
                Pageable pageable
        );
      @Query("SELECT new com.event_core_service.eventcoreservice.dto.response.EventCategoryCountDTO(e.category,COUNT(e)) FROM Event e GROUP BY e.category" )
    List<EventCategoryCountDTO> countGroupByCategory();
    @Query("SELECT new com.event_core_service.eventcoreservice.dto.response.EventStatusCountDTO(e.status,COUNT(e)) FROM Event e GROUP BY e.status" )
      List<EventStatusCountDTO> countGroupByStatus();
    }


