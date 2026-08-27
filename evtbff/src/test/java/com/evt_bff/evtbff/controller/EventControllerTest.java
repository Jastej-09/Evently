package com.evt_bff.evtbff.controller;

import com.evt_bff.evtbff.client.EventClient;
import com.evt_bff.evtbff.client.NotificationClient;
import com.evt_bff.evtbff.config.SecurityConfig;
import com.evt_bff.evtbff.service.JwtService;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import com.evt_bff.evtbff.dto.response.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import feign.FeignException;
import java.util.UUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.Mockito.when;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.evt_bff.evtbff.config.SecurityConfig;
import org.springframework.context.annotation.Import;


//@Import(SecurityConfig.class)
@WebMvcTest(EventController.class)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private EventClient eventClient;

    @MockitoBean
    private NotificationClient notificationClient;

    @Test
    void createEvent_success() throws Exception {

        ResponseDTO expectedResponse = new ResponseDTO();

        expectedResponse.setId("123");
        expectedResponse.setEventName("Coldplay Concert");
        expectedResponse.setOrganizerName("Jay Events");
        expectedResponse.setOrganizerMobile("9999999999");
        expectedResponse.setCity("Delhi");
        expectedResponse.setCategory("MUSIC");
        expectedResponse.setStatus("DRAFT");

        when(eventClient.createEvent(any()))
                .thenReturn(expectedResponse);

        String requestJson = """
                {
                    "eventName": "Coldplay Concert",
                    "organizerName": "Jay Events",
                    "organizerMobile": "9999999999",
                    "city": "Delhi",
                    "category": "MUSIC"
                }
                """;

        mockMvc.perform(
                        post("/api/v1/events")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestJson)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Event created"))
                .andExpect(jsonPath("$.data.id").value("123"))
                .andExpect(jsonPath("$.data.eventName").value("Coldplay Concert"))
                .andExpect(jsonPath("$.data.organizerName").value("Jay Events"))
                .andExpect(jsonPath("$.data.organizerMobile").value("9999999999"))
                .andExpect(jsonPath("$.data.city").value("Delhi"))
                .andExpect(jsonPath("$.data.category").value("MUSIC"))
                .andExpect(jsonPath("$.data.status").value("DRAFT"));
    }

    @WithMockUser(roles = "ADMIN")

    @Test
    void createEvent_validationFailure_badRequest() throws Exception {

        String invalidRequestJson = """
            {
                "eventName": "",
                "organizerName": "",
                "organizerMobile": "",
                "city": "",
                "category": null
            }
            """;

        mockMvc.perform(
                        post("/api/v1/events")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(invalidRequestJson)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.data").doesNotExist());
    }
    @Test
    void getEvent_downstreamNotFound_returns404() throws Exception {

        UUID eventId = UUID.randomUUID();

        FeignException.NotFound notFound =
                new FeignException.NotFound(
                        "Event not found",
                        feign.Request.create(
                                feign.Request.HttpMethod.GET,
                                "/open/v1/events/" + eventId,
                                java.util.Collections.emptyMap(),
                                null,
                                null,
                                null
                        ),
                        null,
                        java.util.Collections.emptyMap()
                );

        when(eventClient.getEvent(eventId))
                .thenThrow(notFound);

        mockMvc.perform(
                        get("/api/v1/events/" + eventId)
                )
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Event not found"))
                .andExpect(jsonPath("$.data").doesNotExist());
    }
//    @Test
//    void missingToken_returns401() throws Exception {
//
//        UUID eventId = UUID.randomUUID();
//
//        mockMvc.perform(
//                        get("/api/v1/events/" + eventId)
//                )
//                .andExpect(status().isUnauthorized());
//    }


}
