package com.example.project2.domain.service;

import com.example.project2.domain.Comment;
import com.example.project2.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommentServiceTest {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    void commentTest(){
        commentService.init();

//        commentRepository.findAll().forEach(System.out::println);
        commentService.updateSomething();
        commentService.insertSomething();
    }


}