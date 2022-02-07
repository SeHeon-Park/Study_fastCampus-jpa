package com.example.project2.domain;

import com.example.project2.domain.listener.Auditable;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass                                       // 이 클래스의 필드를 상속받는 Entity의 컬럼으로 포함시켜 주겠다.(extends BaseEntity 하면됨_)
@EntityListeners(value = AuditingEntityListener.class)  // 기본적으로 제공 Listener 클래스 받기
public class BaseEntity implements Auditable {
    @CreatedDate
    @Column(columnDefinition = "datetime(6) default now(6) comment '생성시간'", nullable = false, updatable = false)
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(columnDefinition = "datetime(6) default now(6) comment '생성시간'",  nullable = false)
    private LocalDateTime updateAt;
}
