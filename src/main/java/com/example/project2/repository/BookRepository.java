package com.example.project2.repository;

import com.example.project2.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Modifying
    @Query(value="upadate book set category=none", nativeQuery = true)
    void update();

}
