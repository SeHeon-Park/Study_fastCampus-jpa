package com.example.project2.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Publisher extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL) // cascade와 공통점: 상위객체가 delete되면 하위객체까지 delete됨
                                                                // false(default): set null과 같이 단순히 연관관계만 끊어줌 , true: 필요 없어진(연관 관계 끊어진) 객체 제거

    @JoinColumn(name = "publisher_id")
    @ToString.Exclude
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book){
        this.books.add(book);
    }
}
