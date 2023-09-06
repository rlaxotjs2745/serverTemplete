package com.ain.fourbyfour.util;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;


@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract class DateAuditingEntity implements Serializable {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDT;

    @LastModifiedDate
    @Column
    private LocalDateTime modifiedDT;
}
