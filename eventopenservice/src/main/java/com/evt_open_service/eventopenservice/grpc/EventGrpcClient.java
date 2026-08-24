package com.evt_open_service.eventopenservice.grpc;


import com.evently.grpc.event.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventGrpcClient {

    private final EventServiceGrpc.EventServiceBlockingStub stub;

    public GetEventResponse getEvent(
            GetEventRequest request) {

        return stub.getEvent(request);
    }
    public CreateEventResponse createEvent(
            CreateEventRequest request) {

        return stub.createEvent(request);
    }
    public UpdateEventStatusResponse updateEventStatus(
            UpdateEventStatusRequest request) {
        System.out.println("ok till here");
        return stub.updateEventStatus(request);
    }

    public ListEventsResponse listEvents(ListEventsRequest request) {
        return stub.listEvents(request);
    }

    public GetEventStatsResponse getEventStats(GetEventStatsRequest request) {
        return stub.getEventStats(request);
    }
}