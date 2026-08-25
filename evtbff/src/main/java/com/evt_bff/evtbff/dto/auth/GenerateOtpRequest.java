package com.evt_bff.evtbff.dto.auth;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record GenerateOtpRequest(

        @NotBlank(message = "Mobile is required")
        @Pattern(
                regexp = "^[0-9]{10}$",
                message = "Mobile must be 10 digits"
        )
        String mobile

) {
}