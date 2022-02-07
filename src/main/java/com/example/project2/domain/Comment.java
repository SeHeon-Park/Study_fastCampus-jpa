package com.example.project2.domain;

import com.example.project2.domain.listener.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@DynamicInsert // 값중에 실제로 insert문에 data가 존재하는 것들만 포함시켜서 실행
@DynamicUpdate // 영향을 받은 culumn에 대해서만 처리
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne
    @ToString.Exclude
    private Review review;

    @Column(columnDefinition = "datetime(6) default now(6)")
    private LocalDateTime commentedAt;

}
