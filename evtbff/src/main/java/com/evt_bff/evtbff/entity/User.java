package com.evt_bff.evtbff.entity;

import com.evt_bff.evtbff.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true)
    private String mobile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole role;
}