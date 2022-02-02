package com.example.project2.domain;

import com.example.project2.domain.listener.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//@DynamicUpdate   // 실제 값이 변경된 컬럼으로만 update 쿼리를 만드는 기능
public class Book extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private Long authorId;

    @OneToOne(mappedBy = "book")  // BookReviewInfo엔티티에 있는 Book필드와 매핑(BookReviewInfo가 bookId라는 외래키를 가지고 있으므로 BookReviewInfo의 Book을 주인으로 한다!)
                                  // 안해주면 원래 BookReviewInfo에 있던 Book과 매핑이 안되서 2개 생성됨
    @ToString.Exclude             // toString이 순환참조가 걸리는 등 오류발생할때 제거해줌
    private BookReviewInfo bookReviewInfo;

    @OneToMany(mappedBy = "book")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    @ToString.Exclude
    private Publisher publisher;

    //@ManyToMany
    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();


    public void addBookAndAuthors(BookAndAuthor... bookAndAuthor){
        Collections.addAll(this.bookAndAuthors, bookAndAuthor);
    }
}
