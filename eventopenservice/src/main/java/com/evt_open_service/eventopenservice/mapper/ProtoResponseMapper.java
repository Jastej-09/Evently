package com.evt_open_service.eventopenservice.mapper;

import com.evently.grpc.event.*;
import com.evt_open_service.eventopenservice.dto.response.ResponseDTO;
import com.evt_open_service.eventopenservice.dto.response.StatsResponseDTO;
import com.evt_open_service.eventopenservice.enums.EventStatus;
import com.evt_open_service.eventopenservice.enums.EventCategory;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ProtoResponseMapper {

    public ResponseDTO toResponseDTO(GetEventResponse grpcResponse) {
        Event event = grpcResponse.getEvent();
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setId(UUID.fromString(event.getId()));
        responseDTO.setEventName(event.getEventName());
        responseDTO.setOrganizerName(event.getOrganizerName());
        responseDTO.setOrganizerMobile(event.getOrganizerMobile());
        responseDTO.setCity(event.getCity());
        responseDTO.setCategory(event.getCategory());
        responseDTO.setStatus( event.getStatus());
        return responseDTO;
    }
    public ResponseDTO toUpdateResponseDTO(UpdateEventStatusResponse grpcResponse) {
        Event event = grpcResponse.getEvent();
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setId(UUID.fromString(event.getId()));
        responseDTO.setEventName(event.getEventName());
        responseDTO.setOrganizerName(event.getOrganizerName());
        responseDTO.setOrganizerMobile(event.getOrganizerMobile());
        responseDTO.setCity(event.getCity());
        responseDTO.setCategory(event.getCategory());
        responseDTO.setStatus( event.getStatus());
        return responseDTO;
    }

    public ResponseDTO toCreateResponseDTO(CreateEventResponse grpcResponse) {
        Event event = grpcResponse.getEvent();
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setId(
                UUID.fromString(event.getId())
        );
        responseDTO.setEventName(
                event.getEventName()
        );
        responseDTO.setOrganizerName(
                event.getOrganizerName()
        );
        responseDTO.setOrganizerMobile(
                event.getOrganizerMobile()
        );
        responseDTO.setCity(
                event.getCity()
        );
        responseDTO.setCategory(
                        event.getCategory()
        );
        responseDTO.setStatus(
                        event.getStatus()
        );
        if (event.hasCreatedOn()) {
            responseDTO.setCreatedOn(
                    LocalDateTime.ofInstant(
                            Instant.ofEpochSecond(
                                    event.getCreatedOn().getSeconds(),
                                    event.getCreatedOn().getNanos()
                            ),
                            ZoneId.systemDefault()
                    )
            );
        }

        if (event.hasModifiedOn()) {
            responseDTO.setModifiedOn(
                    LocalDateTime.ofInstant(
                            Instant.ofEpochSecond(
                                    event.getModifiedOn().getSeconds(),
                                    event.getModifiedOn().getNanos()
                            ),
                            ZoneId.systemDefault()
                    )
            );
        }

        return responseDTO;
    }
    public StatsResponseDTO toStatsResponseDTO(GetEventStatsResponse grpcResponse) {
        StatsResponseDTO statsResponseDTO = new StatsResponseDTO();

        statsResponseDTO.setTotalEvents(grpcResponse.getTotalEvents());

        Map<EventStatus, Long> byStatus = grpcResponse.getByStatusList().stream()
                .collect(Collectors.toMap(
                        (StatusCount sc) -> EventEnumMapper.toJpaStatus(sc.getStatus()),
                        StatusCount::getCount,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
        statsResponseDTO.setByStatus(byStatus);

        Map<EventCategory, Long> byCategory = grpcResponse.getByCategoryList().stream()
                .collect(Collectors.toMap(
                        (CategoryCount cc) -> EventEnumMapper.toJpaCategory(cc.getCategory()),
                        CategoryCount::getCount,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
        statsResponseDTO.setByCategory(byCategory);

        return statsResponseDTO;
    }

    public com.evt_open_service.eventopenservice.dto.response.PageResponse<ResponseDTO> toPageResponse(ListEventsResponse grpcResponse) {
        java.util.List<ResponseDTO> content = grpcResponse.getEventsList().stream().map(event -> {
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setId(UUID.fromString(event.getId()));
            responseDTO.setEventName(event.getEventName());
            responseDTO.setOrganizerName(event.getOrganizerName());
            responseDTO.setOrganizerMobile(event.getOrganizerMobile());
            responseDTO.setCity(event.getCity());
            responseDTO.setCategory(event.getCategory());
            responseDTO.setStatus(event.getStatus());
            return responseDTO;
        }).collect(Collectors.toList());

        return new com.evt_open_service.eventopenservice.dto.response.PageResponse<>(
                content,
                grpcResponse.getTotalElements(),
                grpcResponse.getTotalPages(),
                grpcResponse.getPage(),
                grpcResponse.getSize()
        );
    }
}