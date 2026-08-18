package com.event_core_service.eventcoreservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @CreatedDate
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdOn;


    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedOn;

}
