package com.example.project2.domain.service;

import com.example.project2.domain.Author;
import com.example.project2.domain.Book;
import com.example.project2.repository.AuthorRepository;
import com.example.project2.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;


@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private EntityManager entityManager;
    private final AuthorService authorService;

    @Transactional(propagation = Propagation.REQUIRED)
                   // RuntimeException: Exception이 일어나면 roll back됨, CheckedException: roll back되지 않고 그냥 commit됨
                   // (rollbackFor = Exception.class)써주면 CheckedException도 roll back됨
                   // 만약 @Transactional이 붙어잇는 함수를 다른함수를 통해 불러주면 @Transactional은 무시됨
    public void putBookAndAuthor(){
        Book book = new Book();
        book.setName("JPA 시작하기");
        bookRepository.save(book);
        try {
            authorService.putAuthor();
        }catch(RuntimeException e){}
//        Author author = new Author();
//        author.setName("martin");
//        authorRepository.save(author);

//        throw new RuntimeException("오류가 나서 DB commit이 발생하지 않았습니다.");

    }



    @Transactional(isolation = Isolation.SERIALIZABLE)      // commit이 일어나지 않은 transaction이 존재 하게 되면 lock을 통해 waiting
               // (isolation = Isolation.REPEATABLE_READ)   // transaction내에서 반복해서 값을 조회하더라도 동일한 값이 리턴 보장(별도의 스냅샷에 초기값 저장해둠)(phantom)
               // (isolation = Isolation.READ_UNCOMMITTED)  // commit되지 않은 데이터를 읽을수 있음(unrepeatable read)
               // (isolation = Isolation.READ_COMMITTED)    // commit된 데이터만 읽어온다(dirty read)
    public void get(Long id){
        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

        entityManager.clear();          // READ_COMMITTED일때 중간에 commit을 해주어도 entity cache에 데이터가 남아있어 commit된 정보가 출력이 안된다. 따라서 clear해주어야 함

        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

        bookRepository.update();

        entityManager.clear();


//        Book book  = bookRepository.findById(id).get();
//        book.setName("바뀔까?");
//        bookRepository.save(book);
    }

}
