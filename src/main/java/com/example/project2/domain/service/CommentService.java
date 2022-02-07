package com.example.project2.domain.service;

import com.example.project2.domain.Comment;
import com.example.project2.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public void init(){
        for (int i=0; i<10; i++){
            Comment comment = new Comment();
            comment.setComment("최고예요");

            commentRepository.save(comment);
        }
    }

    @Transactional(readOnly = true)  // dirty check안해주기!(dirty check도 많아지면 효율x)
    public void updateSomething(){
        List<Comment> comments = commentRepository.findAll();
        for(Comment comment : comments){
            comment.setComment("별로예요");
            commentRepository.save(comment);  // save없어도 update됨!(dirty check)
        }
    }

    @Transactional
    public void insertSomething(){
//        Comment comment = new Comment();                      // 새로 생성된 Comment라 영속화되어있지 않음
        Comment comment = commentRepository.findById(1L).get(); // 이미 들어간거를 가져오는거라 영속화 되어있음
        comment.setComment("이건뭐죠?");

//        commentRepository.save(comment);  // 새로생긴건 save안해주면 영속화가 안됨(dirty check 안됨)
    }
}
