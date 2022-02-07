package com.example.project2.repository;

import com.example.project2.domain.Book;
import com.example.project2.repository.dto.BookNameAndCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Tuple;
import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Modifying
    @Query(value="upadate book set category=none", nativeQuery = true)
    void update();

    List<Book> findByCategoryIsNull();

    List<Book> findAllByDeletedFalse();

    List<Book> findByCategoryIsNullAndDeletedFalse();

    List<Book> findByCategoryIsNullAndNameEqualsAndCreateAtGreaterThanEqualAndUpdateAtGreaterThanEqual(String name, LocalDateTime createAt, LocalDateTime updateAt);

    @Query(value="select b from Book b "
        + "where name = :name and createAt >= :createAt and updateAt >= :updateAt and category is null") //JPQL, ":이름"대신 "?n" 써도됨(?n: 파라미터 n번째)
    List<Book> findByNameRecently(@Param("name")String name,
                                  @Param("createAt")LocalDateTime createAt,
                                  @Param("updateAt")LocalDateTime updateAt);

    //BookNameAndCategory 인터페이스로 선언해서 활용
//    @Query(value= "select b.name as name, b.category as category from Book b")
//    List<BookNameAndCategory> findBookNameAndCategory();

    //BookNameAndCategory 클래스로 선언해서 활용
    @Query(value= "select new com.example.project2.repository.dto.BookNameAndCategory(b.name, b.category) from Book b")
    List<BookNameAndCategory> findBookNameAndCategory();

    //paging 기법
//    @Query(value= "select new com.example.project2.repository.dto.BookNameAndCategory(b.name, b.category) from Book b")
//    List<BookNameAndCategory> findBookNameAndCategory(Pageable pageable);

    @Query(value = "select * from book", nativeQuery = true) // nativeQuery는 entity가 아니라 table이름이 from절로 와야함
                                                             // nativeQuery: SQL을 직접 사용할 수 있는 기능
                                                             // where조건 달아줬던것도 무시해주네?
    List<Book> findAllCustom();

    @Transactional  // nativeQuery를 실행하는 경우에는 @Transactional을 직접 정의해주어야함
    @Modifying      // 데이터에 변경이 일어나는 INSERT, UPDATE, DELETE, DDL 에서 사용
    @Query(value="update book set category = 'IT전문서'", nativeQuery = true)
    int updateCategories();

    @Query(value = "show tables", nativeQuery = true)
    List<String> showTables();

    @Query(value = "select * from book order by id desc limit 1", nativeQuery = true)
    Map<String, Object> findRawRecord();
}
