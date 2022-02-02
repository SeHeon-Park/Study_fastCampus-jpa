package com.example.project2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BookReviewInfo extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(optional = false)
    // 1대1로 연관관계 매핑함(optional=false : not null, mappeBy: 해당키를 이 테이블에서 더이상 가지지 않음)
    private Book book;
    private float averageReviewScore;
    private int reviewCount;

}
