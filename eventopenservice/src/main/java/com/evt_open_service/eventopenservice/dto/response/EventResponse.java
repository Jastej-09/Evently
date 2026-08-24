package com.evt_open_service.eventopenservice.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {

    private String id;

    private String eventName;

    private String organizerName;

    private String organizerMobile;

    private String city;

    private String category;

    private String status;

    private Instant createdOn;

    private Instant modifiedOn;
}
