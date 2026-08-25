package com.evt_bff.evtbff.controller;

import com.evt_bff.evtbff.dto.auth.AuthResponse;
import com.evt_bff.evtbff.dto.auth.GenerateOtpRequest;
import com.evt_bff.evtbff.dto.auth.VerifyOtpRequest;
import com.evt_bff.evtbff.entity.User;
import com.evt_bff.evtbff.enums.UserRole;
import com.evt_bff.evtbff.responseenvelope.ResponseEnvelope;
import com.evt_bff.evtbff.service.AuthService;
import com.evt_bff.evtbff.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private OtpService otpService;
    private AuthService authService;
    AuthController(OtpService otpService, AuthService authService) {
        this.otpService = otpService;
        this.authService = authService;
    }
    @PostMapping("/generate-otp")
    public ResponseEnvelope<String> generateOtp(@Validated @RequestBody GenerateOtpRequest generateOtpRequest){
        otpService.generateOtp(generateOtpRequest.mobile());
        return new ResponseEnvelope<>(true,"OTP generated Successfully for :",generateOtpRequest.mobile());

    }
    @PostMapping("/verifyOTP")
    public ResponseEnvelope<AuthResponse> verifyOtp(@Validated @RequestBody VerifyOtpRequest verifyOtpRequest){
        AuthResponse response = authService.verifyOtp(verifyOtpRequest.mobile(),verifyOtpRequest.otp());
        return new ResponseEnvelope<>(true,"OTP verified",response);
    }
}
