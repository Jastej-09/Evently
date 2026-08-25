package com.evt_bff.evtbff.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
@Slf4j
public class OtpService {

    private final StringRedisTemplate redisTemplate;

    public void generateOtp(String mobile) {

        int otp = ThreadLocalRandom.current()
                .nextInt(100000, 1000000);

        String key = "otp:" + mobile;

        redisTemplate.opsForValue()
                .set(key, String.valueOf(otp), Duration.ofMinutes(5));

        log.info("OTP generated mobile={} otp={}", mobile, otp);
    }
    public boolean verifyOtp(String mobile, String otp) {
        String key = "otp:" + mobile;
        String storedOtp =  redisTemplate.opsForValue().get(key);
        if (storedOtp != null && storedOtp.equals(otp)) {
            return true;
        }else  {
            return false;
        }

    }
}