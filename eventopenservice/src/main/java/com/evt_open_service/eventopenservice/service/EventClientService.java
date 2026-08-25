package com.evt_open_service.eventopenservice.service;

import com.evently.grpc.event.*;
import com.evt_open_service.eventopenservice.dto.request.CreateEventRequestDTO;
import com.evt_open_service.eventopenservice.dto.response.*;
import com.evt_open_service.eventopenservice.grpc.EventGrpcClient;
import com.evt_open_service.eventopenservice.mapper.EventEnumMapper;
import com.evt_open_service.eventopenservice.mapper.ProtoResponseMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventClientService {
    private final EventGrpcClient eventGrpcClient;
    private final ProtoResponseMapper mapper;
    private final kafkaEventProducerService kafkaProducer;

    public EventClientService(EventGrpcClient eventGrpcClient, ProtoResponseMapper mapper,
                              kafkaEventProducerService kafkaProducer) {
        this.eventGrpcClient = eventGrpcClient;
        this.mapper = mapper;
        this.kafkaProducer = kafkaProducer;
    }

    public ResponseDTO getEvent(UUID id) {
        return mapper.toResponseDTO(eventGrpcClient.getEvent(GetEventRequest.newBuilder()
                .setId(id.toString()).build()));
    }

    public ResponseDTO createEvent(CreateEventRequestDTO request) {
        CreateEventRequest grpcRequest = CreateEventRequest.newBuilder()
                .setEventName(request.getEventName())
                .setOrganizerName(request.getOrganizerName())
                .setOrganizerMobile(request.getOrganizerMobile())
                .setCity(request.getCity())
                .setCategory(EventEnumMapper.toProtoCategory(request.getCategory()))
                .build();
        return mapper.toCreateResponseDTO(eventGrpcClient.createEvent(grpcRequest));
    }

    public ResponseDTO updateEventStatus(UUID id, com.evt_open_service.eventopenservice.enums.EventStatus status) {
        UpdateEventStatusResponse grpcResponse = eventGrpcClient.updateEventStatus(
                UpdateEventStatusRequest.newBuilder().setId(id.toString())
                        .setStatus(EventEnumMapper.toProtoStatus(status)).build());
        ResponseDTO event = mapper.toUpdateResponseDTO(grpcResponse);
        if (event.getStatus() == EventStatus.PUBLISHED) {
            kafkaProducer.publishEventPublished(event.getId().toString(), event.getEventName(),
                    event.getCity(), event.getCategory().name());
//            kafkaTemplate.send(KafkaTopics.EVENT_PUBLISHED, entityId, message);
        } else if (event.getStatus() == EventStatus.CANCELLED || event.getStatus() == EventStatus.SOLD_OUT) {
            kafkaProducer.publishEventStatusChanged(event.getId().toString(), event.getEventName(),
                    event.getCity(), event.getCategory().name());
        }
        return event;
    }

    public PageResponse<ResponseDTO> getFilteredResult(String city,
                                                        com.evt_open_service.eventopenservice.enums.EventCategory category,
                                                        com.evt_open_service.eventopenservice.enums.EventStatus status,
                                                        int page, int size) {
        ListEventsRequest.Builder request = ListEventsRequest.newBuilder().setPage(page).setSize(size);
        if (city != null) request.setCity(city);
        if (category != null) request.setCategory(EventEnumMapper.toProtoCategory(category));
        if (status != null) request.setStatus(EventEnumMapper.toProtoStatus(status));
        return mapper.toPageResponse(eventGrpcClient.listEvents(request.build()));
    }

    public StatsResponseDTO getStats() {
        return mapper.toStatsResponseDTO(eventGrpcClient.getEventStats(GetEventStatsRequest.newBuilder().build()));
    }
}
