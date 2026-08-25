package com.event_core_service.eventcoreservice.grpc;

import com.event_core_service.eventcoreservice.dto.request.CreateEventRequestDTO;
import com.event_core_service.eventcoreservice.dto.response.ResponseDTO;
import com.event_core_service.eventcoreservice.mapper.EventEnumMapper;
import com.event_core_service.eventcoreservice.service.EventService;
import com.evently.grpc.event.*;
import io.grpc.stub.StreamObserver;
import io.grpc.stub.annotations.GrpcGenerated;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.data.domain.Page;

import static com.event_core_service.eventcoreservice.mapper.EventEnumMapper.toProtoCategory;
import static com.event_core_service.eventcoreservice.mapper.EventEnumMapper.toProtoStatus;
@Slf4j
@GrpcService
    @RequiredArgsConstructor
    public class EventGrpcService extends EventServiceGrpc.EventServiceImplBase {

    private final EventService eventService;

    @Override
    public void getEvent(
            GetEventRequest request,
            StreamObserver<GetEventResponse> responseObserver) {

        try {

            UUID eventId = UUID.fromString(request.getId());

            ResponseDTO event = eventService.getEvent(eventId);

            Event protoEvent = Event.newBuilder()
                    .setId(event.getId().toString())
                    .setEventName(event.getEventName())
                    .setOrganizerName(event.getOrganizerName())
                    .setOrganizerMobile(event.getOrganizerMobile())
                    .setCity(event.getCity())
                    .setCategory(toProtoCategory(event.getCategory()))
                    .setStatus(toProtoStatus(event.getStatus()))
                    .build();

            GetEventResponse response = GetEventResponse.newBuilder()
                    .setEvent(protoEvent)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (IllegalArgumentException e) {

            responseObserver.onError(
                    Status.INVALID_ARGUMENT
                            .withDescription("Invalid event ID")
                            .asRuntimeException()
            );

        } catch (Exception e) {

            responseObserver.onError(
                    Status.INTERNAL
                            .withDescription("Failed to fetch event")
                            .asRuntimeException()
            );
        }
    }
    public void createEvent(
            CreateEventRequest request,
            StreamObserver<CreateEventResponse> responseObserver){
        try {

            CreateEventRequestDTO requestDTO = new CreateEventRequestDTO();
            requestDTO.setEventName(request.getEventName());
            requestDTO.setOrganizersMobile(request.getOrganizerMobile());
            requestDTO.setOrganizersName(request.getOrganizerName());
            requestDTO.setCategory(EventEnumMapper.toJpaCategory(request.getCategory()));
            requestDTO.setCity(request.getCity());
            requestDTO.setEventName(request.getEventName());


            ResponseDTO responsedto = eventService.createEvent(requestDTO);

            Event protoEvent = Event.newBuilder()
                    .setId(responsedto.getId().toString())
                    .setEventName(responsedto.getEventName())
                    .setOrganizerName(responsedto.getOrganizerName())
                    .setOrganizerMobile(responsedto.getOrganizerMobile())
                    .setCity(responsedto.getCity())
                    .setCategory(toProtoCategory(responsedto.getCategory()))
                    .setStatus(toProtoStatus(responsedto.getStatus()))
                    .build();

            CreateEventResponse response = CreateEventResponse.newBuilder()
                    .setEvent(protoEvent)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        }catch (IllegalArgumentException e) {

            responseObserver.onError(
                    Status.INVALID_ARGUMENT
                            .withDescription("Invalid event ID")
                            .asRuntimeException()
            );

        }  catch (Exception e) {
        log.error("createEvent failed", e);  
        responseObserver.onError(Status.INTERNAL
                .withDescription(e.getMessage())   
                .withCause(e)
                .asRuntimeException());

        }
    }
    @Override
    public void updateEventStatus(UpdateEventStatusRequest request, StreamObserver<UpdateEventStatusResponse> responseObserver ){
        ResponseDTO responseDTO = eventService.updateEventStatus((UUID.fromString(request.getId())),EventEnumMapper.toJpaStatus(request.getStatus()));

        Event protoEvent = Event.newBuilder()
                .setId(responseDTO.getId().toString())
                .setEventName(responseDTO.getEventName())
                .setOrganizerName(responseDTO.getOrganizerName())
                .setOrganizerMobile(responseDTO.getOrganizerMobile())
                .setCity(responseDTO.getCity())
                .setCategory(toProtoCategory(responseDTO.getCategory()))
                .setStatus(toProtoStatus(responseDTO.getStatus()))
                .build();

        UpdateEventStatusResponse response = UpdateEventStatusResponse.newBuilder()
                .setEvent(protoEvent)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void listEvents(ListEventsRequest request, StreamObserver<ListEventsResponse> responseObserver) {
        try {
            String city = request.getCity().isEmpty() ? null : request.getCity();
            
            com.event_core_service.eventcoreservice.enums.EventCategory category = 
                (request.getCategory() == com.evently.grpc.event.EventCategory.EVENT_CATEGORY_UNSPECIFIED || request.getCategory() == com.evently.grpc.event.EventCategory.UNRECOGNIZED) 
                ? null 
                : EventEnumMapper.toJpaCategory(request.getCategory());
                
            com.event_core_service.eventcoreservice.enums.EventStatus status = 
                (request.getStatus() == com.evently.grpc.event.EventStatus.EVENT_STATUS_UNSPECIFIED || request.getStatus() == com.evently.grpc.event.EventStatus.UNRECOGNIZED) 
                ? null 
                : EventEnumMapper.toJpaStatus(request.getStatus());
            int page = request.getPage() > 0 ? request.getPage() : 0;
            int size = request.getSize() > 0 ? request.getSize() : 10;
            org.springframework.data.domain.Page<ResponseDTO> filteredResult = eventService.getFilteredResult(city, category, status, page, size);
            java.util.List<Event> protoEvents = filteredResult.getContent().stream().map(responseDTO -> Event.newBuilder()
                    .setId(responseDTO.getId().toString())
                    .setEventName(responseDTO.getEventName())
                    .setOrganizerName(responseDTO.getOrganizerName())
                    .setOrganizerMobile(responseDTO.getOrganizerMobile())
                    .setCity(responseDTO.getCity())
                    .setCategory(toProtoCategory(responseDTO.getCategory()))
                    .setStatus(toProtoStatus(responseDTO.getStatus()))
                    .build()).collect(java.util.stream.Collectors.toList());
            ListEventsResponse response = ListEventsResponse.newBuilder()
                    .addAllEvents(protoEvents)
                    .setTotalElements(filteredResult.getTotalElements())
                    .setTotalPages(filteredResult.getTotalPages())
                    .setPage(filteredResult.getNumber())
                    .setSize(filteredResult.getSize())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            log.error("listEvents failed", e);
            responseObserver.onError(Status.INTERNAL
                    .withDescription(e.getMessage())
                    .withCause(e)
                    .asRuntimeException());
        }
    }

    @Override
    public void getEventStats(GetEventStatsRequest request,
                              StreamObserver<GetEventStatsResponse> responseObserver) {
        try {
            com.event_core_service.eventcoreservice.dto.response.StatsResponseDTO stats =
                    eventService.getStats();

            java.util.List<StatusCount> statusCounts = stats.getByStatus()
                    .entrySet().stream()
                    .map(entry -> StatusCount.newBuilder()
                            .setStatus(toProtoStatus(entry.getKey()))
                            .setCount(entry.getValue())
                            .build())
                    .collect(java.util.stream.Collectors.toList());

            java.util.List<CategoryCount> categoryCounts = stats.getByCategory()
                    .entrySet().stream()
                    .map(entry -> CategoryCount.newBuilder()
                            .setCategory(toProtoCategory(entry.getKey()))
                            .setCount(entry.getValue())
                            .build())
                    .collect(java.util.stream.Collectors.toList());

            GetEventStatsResponse response = GetEventStatsResponse.newBuilder()
                    .setTotalEvents(stats.getTotalEvents())
                    .addAllByStatus(statusCounts)
                    .addAllByCategory(categoryCounts)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            log.error("getEventStats failed", e);
            responseObserver.onError(
                    Status.INTERNAL
                            .withDescription(e.getMessage())
                            .withCause(e)
                            .asRuntimeException()
            );
        }
    }
}


