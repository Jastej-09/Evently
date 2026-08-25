package com.evt_bff.evtbff.dto.auth;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record VerifyOtpRequest (@NotNull @Pattern(
        regexp = "^[0-9]{10}$",
        message = "Mobile must be 10 digits"
) String mobile, @NotNull(message = "OTP must be Entered") @Pattern(
        regexp = "^[0-9]{6}$",
        message = "Mobile must be 10 digits")
        String otp) {

}
