package com.evt_open_service.eventopenservice.mapper;

import com.evently.grpc.event.CreateEventResponse;
import com.evently.grpc.event.UpdateEventStatusResponse;
import com.evt_open_service.eventopenservice.dto.response.ResponseDTO;
import com.evently.grpc.event.Event;
import com.evently.grpc.event.GetEventResponse;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

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
}