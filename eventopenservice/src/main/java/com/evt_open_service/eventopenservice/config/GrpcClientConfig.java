package com.evt_open_service.eventopenservice.config;

import com.evently.grpc.event.EventServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfig {

    @Value("${GRPC_HOST:localhost}")
    private String grpcHost;

    @Value("${GRPC_PORT:9090}")
    private int grpcPort;

    @Bean
    public EventServiceGrpc.EventServiceBlockingStub eventServiceBlockingStub(
            ManagedChannel channel) {

        return EventServiceGrpc.newBlockingStub(channel);
    }

    @Bean
    public ManagedChannel managedChannel() {
        return ManagedChannelBuilder
                .forAddress(grpcHost, grpcPort)
                .usePlaintext()
                .build();
    }
}



