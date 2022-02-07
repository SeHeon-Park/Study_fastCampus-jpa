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

    @Transactional(propagation = Propagation.REQUIRED)      // 재활용하여 사용해서, 둘다 commit/rollback됨(동일)(만약 호출쪽 transaction 없다면 새로생성)
                //(propagation = Propagation.REQUIRES_NEW)  // 호출하는 쪽에 rollback/commit과 관련없이 새로운 transaction 생성(각각)
                //(propagation = Propagation.NESTED)        // 호출하는쪽의 transaction을 그대로 활용하지만 약간은 분리되어 실행(호출하는쪽 오류 따라감, 받는쪽 오류 독립)
                //(propagation = Propagation.SUPPORTS)      // 재활용하여 사용해서, 둘다 commit/rollback됨(동일)(만약 호출쪽 transaction 없으면 굳이 새로 생성x)
                //(propagation = Propagation.NOT_SUPPORTED) // 호출하는 쪽과 별개로 실행(굳이 transaction없이 실행)
                //(propagation = Propagation.MANDATORY)     // 필수적으로 transaction존재해야됨, 없으면 오류
                //(propagation = Propagation.NEVER)         // transaction 없어야함(있으면 오류)


    public void putAuthor(){
        Author author = new Author();
        author.setName("martin");
        authorRepository.save(author);
        throw new RuntimeException("오류가 발생하였습니다. transaction은 어떻게 될까요?");
    }
}
