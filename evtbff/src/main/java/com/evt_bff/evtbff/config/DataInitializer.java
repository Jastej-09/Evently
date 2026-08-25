package com.evt_bff.evtbff.config;

import com.evt_bff.evtbff.entity.User;
import com.evt_bff.evtbff.enums.UserRole;
import com.evt_bff.evtbff.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataInitializer  {

    private final UserRepository userRepository;

//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("Application started! Running startup logic...");
//
//    }
    @Bean
    CommandLineRunner initializeUsers() {
        return args -> {
            System.out.println("datainitializer works");

            if (userRepository.findByMobile("9999999999").isEmpty()) {
                userRepository.save(
                        User.builder()
                                .mobile("9999999999")
                                .role(UserRole.ADMIN)
                                .build()
                );
            }

            if (userRepository.findByMobile("8888888888").isEmpty()) {
                userRepository.save(
                        User.builder()
                                .mobile("8888888888")
                                .role(UserRole.VIEWER)
                                .build()
                );
            }
        };
    }
}