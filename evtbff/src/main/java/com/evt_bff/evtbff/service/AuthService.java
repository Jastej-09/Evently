package com.evt_bff.evtbff.service;

import com.evt_bff.evtbff.dto.auth.AuthResponse;
import com.evt_bff.evtbff.entity.User;
import com.evt_bff.evtbff.exception.InvalidOtpException;
import com.evt_bff.evtbff.exception.UserNotFoundException;
import com.evt_bff.evtbff.repository.UserRepository;
import com.evt_bff.evtbff.responseenvelope.ResponseEnvelope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Ref;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtSerivce;
    private final UserRepository userRepository;
    private final OtpService otpService;
    private Object String;

    public AuthResponse verifyOtp(String mobile, String otp) {
        Boolean valid = otpService.verifyOtp(mobile,otp);
        if(valid==false){
            throw new InvalidOtpException("Invalid or expired OTP");
        }
        User user =  userRepository.findByMobile(mobile)
                .orElseThrow(() ->  new UserNotFoundException("User not found"));

        String accessToken = jwtSerivce.generateToken(user);

        String RefreshToken = jwtSerivce.generateRefreshToken();
        return new AuthResponse(accessToken,RefreshToken);

    }

}
