package com.evt_bff.evtbff.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignTraceInterceptor {

    @Bean
    public RequestInterceptor traceIdInterceptor() {

        return requestTemplate -> {

            String traceId =
                    MDC.get("traceId");

            if (traceId != null) {

                requestTemplate.header(
                        "X-Trace-Id",
                        traceId
                );
            }
        };
    }
}