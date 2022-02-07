package com.example.project2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Embeddable     // 설정한 클래스가 다른 엔티티의 일부로 저장될 수 있음을 설정
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String city;      // 시
    private String district;  // 구
    @Column(name = "address_detail")
    private String detail;    // 상세주소
    private String zipCode;   // 우편번호
}
