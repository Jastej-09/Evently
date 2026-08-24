package com.evt_open_service.eventopenservice.config;

import com.evently.grpc.event.EventServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfig {

    @Bean
    public EventServiceGrpc.EventServiceBlockingStub eventServiceBlockingStub(
            ManagedChannel channel) {

        return EventServiceGrpc.newBlockingStub(channel);
    }

    @Bean
    public ManagedChannel managedChannel() {
        return ManagedChannelBuilder
                .forAddress("localhost", 9090)
                .usePlaintext()
                .build();
    }
}


