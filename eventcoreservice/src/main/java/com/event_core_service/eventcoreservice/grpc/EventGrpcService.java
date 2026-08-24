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
//            CreateEventRequest grpcrequest = CreateEventRequest.newBuilder()
//                    .setEventName(request.getEventName())
//                    .setOrganizerName(request.getOrganizersName())
//                    .setCity(request.getCity())
//                    .setCategory(EventEnumMapper.toProtoCategory(request.getCategory()))
//                    .setOrganizerMobile(request.getOrganizersMobile())
//                    .build();
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
        log.error("createEvent failed", e);  // <-- this line saves you, always keep it
        responseObserver.onError(Status.INTERNAL
                .withDescription(e.getMessage())   // real message, not a hardcoded string
                .withCause(e)
                .asRuntimeException());

        }
    }
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
}


