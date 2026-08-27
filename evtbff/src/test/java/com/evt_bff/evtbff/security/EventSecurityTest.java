//package com.evt_bff.evtbff.security;
//
//import com.evt_bff.evtbff.client.EventClient;
//import com.evt_bff.evtbff.client.NotificationClient;
//import com.evt_bff.evtbff.config.SecurityConfig;
//import com.evt_bff.evtbff.service.AuthService;
//import com.evt_bff.evtbff.service.JwtService;
//import com.evt_bff.evtbff.service.OtpService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
//import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.UUID;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class EventSecurityTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockitoBean
//    private EventClient eventClient;
//
//    @MockitoBean
//    private NotificationClient notificationClient;
//    @MockitoBean
//    private AuthService authService;
//    @MockitoBean
//    private JwtService jwtService;
//    @MockitoBean
//    private OtpService otpService;
//
//
//
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
//}
//
