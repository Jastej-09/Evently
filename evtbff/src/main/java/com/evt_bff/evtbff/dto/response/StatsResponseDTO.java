package com.evt_bff.evtbff.dto.response;

import lombok.Data;

import java.util.Map;

@Data
public class StatsResponseDTO {

    private long totalEvents;

    private Map<String, Long> byStatus;

    private Map<String, Long> byCategory;
}
