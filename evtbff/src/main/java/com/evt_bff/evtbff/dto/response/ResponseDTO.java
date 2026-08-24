package com.evt_bff.evtbff.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ResponseDTO {

    private String id;
    private String eventName;
    private String organizerName;
    private String organizerMobile;
    private String city;
    private String category;
    private String status;
}