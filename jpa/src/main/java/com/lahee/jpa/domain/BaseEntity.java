package com.lahee.jpa.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

//해당 엔티티가 공유하는 속성을 모으기 위한 상위 Entity를 나타내는 어노테이션이다.
@Data
@MappedSuperclass
//Entity의 변화를 지켜보는 클래스이다.
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(updatable = true)
    private Instant updatedAt;

    //슈퍼클래스
    //상속 대상 클래스를 만들었다.
}
