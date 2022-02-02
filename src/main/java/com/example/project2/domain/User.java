package com.example.project2.domain;

import com.example.project2.domain.listener.Auditable;
import com.example.project2.domain.listener.UserEntityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(callSuper = true)             // 상속받는 super class의 정보를 toString에 포함하도록 지정
@EqualsAndHashCode(callSuper = true)    // 상속받는 super class의 정보를 EqualsAndHashCode에 포함하도록 지정

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@EntityListeners(value = UserEntityListener.class)
@Table(name = "user", indexes={@Index(columnList = "name")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
                      // name 컬럼으로 인덱스 테이블이 새로 추가됨(가독성 up)
                      //@Table의 uniqueConstraints는 여러개의 컬럼을 unique속성으로 가질수있음

public class User extends BaseEntity{
    @Id                                                 // User라는 테이블의 PK값
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 순차적으로 증가
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @Enumerated(value = EnumType.STRING) //EnumType이 STRING이라 "MALE"로 나옴
                                         //안해주면 EnumType이 ORDINAL이라 "0"이나옴
    private Gender gender;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)     // Entity가 어떤 컬럼으로 조인 될지 지정해줌
                                  // User에서 UserHistory가 저장x, 수정x
    @ToString.Exclude
    private List<UserHistory> userHistories = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();


//    @Column(name = "crtdat")        // DB에 crtdat이 오브젝트의 createAt과 매핑
//    @Column(nullable = false)       // DDL커리를 자동으로 생성을 할때 nonNull 컬럼으로 만듦
//    @Column(unique = true)          // 이 한개의 컬럼은 unique속성을 가짐
//    @Column(updatable = false)      // upadate시 해당값을 저장x
//    @Column(insertable = false)     // insert시 해당값을 저장x

//    @CreatedDate
//    private LocalDateTime createAt;
//    @LastModifiedDate
//    private LocalDateTime updateAt;

//    @Transient                       // DB에 반영하지 않고 object로서만 사용하고 싶을때(영속성처리에서 제외됨)
//    private String testData;
//
//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;

    /** listener개념 **/
    // ※ 한번 persist 상태였다가 detached 된 상태에서 그 다음 persist 상태가 될 때, merge 한다고 함

//    @PrePersist   // persist(insert)메소드가 호출되기 전에 실행되는 메소드
//    @PreUpdate    // merge메소드가 호출되기 전에 실행되는 메소드
//    @PreRemove    // delete메소드가 호출되기 전에 실행되는 메소드
//
//    @PostPersist  // persist메소드가 호출된 이후에 실행되는 메소드
//    @PostUpdate   // merge메소드가 호풀된 이후에 실행되는 메소드
//    @PostRemove   // delete메소드가 호풀된 이후에 실행되는 메소드
//    @PostLoad     // select조회가 일어난 직후에 실행되는 메소드

/*  @PrePersist
    public void prePersist(){
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();        // 실수로 세팅안했을때 이거 활용 good
    }
    @PreUpdate
    public void PreUpdate(){
        this.updateAt = LocalDateTime.now();
    }*/

}
