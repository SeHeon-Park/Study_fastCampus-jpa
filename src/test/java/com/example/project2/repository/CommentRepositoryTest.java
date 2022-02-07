package com.example.project2.repository;

import com.example.project2.domain.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/** entity캐시에 있는 값과 db에 있는 값과 불일치시 문제가 생겨 **/
@SpringBootTest
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    void commentTest(){
        Comment comment = new Comment();
//        comment.setComment("별로예요");
//        comment.setCommentedAt(LocalDateTime.now());

        commentRepository.saveAndFlush(comment);

        entityManager.clear();
//        System.out.println(commentRepository.findById(3L).get());
        System.out.println(comment);
        commentRepository.findAll().forEach(System.out::println);

    }
}