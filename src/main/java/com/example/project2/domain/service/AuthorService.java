package com.example.project2.domain.service;

import com.example.project2.domain.Author;
import com.example.project2.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Transactional(propagation = Propagation.REQUIRED) // 호출하는쪽의 transaction을 그대로 활용
                //(propagation = Propagation.REQUIRES_NEW) // 호출하는 쪽에 rollback/commit과 관련없이 새로운 transaction 생성
                //(propagation = Propagation.REQUIRED) // 재활용하여 사용해서, 둘다 commit/rollback됨
    public void putAuthor(){
        Author author = new Author();
        author.setName("martin");
        authorRepository.save(author);
        throw new RuntimeException("오류가 발생하였습니다. transaction은 어떻게 될까요?");
    }
}
