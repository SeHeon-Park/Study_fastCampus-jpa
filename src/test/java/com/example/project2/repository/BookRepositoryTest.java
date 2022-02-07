package com.example.project2.repository;

import com.example.project2.domain.Book;
import com.example.project2.domain.Publisher;
import com.example.project2.domain.Review;
import com.example.project2.domain.User;
import com.example.project2.repository.dto.BookStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void bookTest(){
        Book book = new Book();
        book.setName("JPA 초격차 패키지");
        book.setAuthorId(1L);
        //book.setPublisherId(1L);

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());
    }

    /** N대1 관계 **/
    @Test
    @Transactional
    void bookRelationTest(){
        givenBookAndReview();

        User user = userRepository.findByEmail("1@naver.com");
        System.out.println("Review: " + user.getReviews());
        System.out.println("Book: " + user.getReviews().get(0).getBook());
        System.out.println("Publisher: " + user.getReviews().get(0).getBook().getPublisher());
    }

    private void givenBookAndReview(){
        givenReview(givenUser(), givenBook(givenPublisher()));
    }

    private User givenUser(){
        return userRepository.findByEmail("1@naver.com");
    }

    private Book givenBook(Publisher publisher){
        Book book = new Book();
        book.setName("JPA초격차 패키지");
        book.setPublisher(publisher);
        return bookRepository.save(book);
    }

    private Publisher givenPublisher(){
        Publisher publisher = new Publisher();
        publisher.setName("패스트캠퍼스");
        return publisherRepository.save(publisher);
    }

    private void givenReview(User user, Book book){
        Review review = new Review();
        review.setTitle("내 인생을 바꾼책");
        review.setContent("재밌다");
        review.setScore(5.0f);
        review.setUser(user);
        review.setBook(book);

        reviewRepository.save(review);
    }

    /** ch08-cascade알아보기 **/
    @Test
    void bookCascadeTest(){
        Book book = new Book();
        book.setName("JPA초격차");

        Publisher publisher = new Publisher();
        publisher.setName("패스트캠퍼스");

        book.setPublisher(publisher);  // db에 저장하지않고 연관관계 맺어주면 오류! // cascade의 persist옵션 달아주자!
        bookRepository.save(book);

        System.out.println("books: " + bookRepository.findAll());
        System.out.println("publishers: " + publisherRepository.findAll());


        Book book1 = bookRepository.findById(1L).get();
        book1.getPublisher().setName("슬로우캠퍼스");
        bookRepository.save(book1);

        System.out.println("publishers: " + publisherRepository.findAll()); // cascade의 merge옵션도 달아주어야 겠네?

        Book book2 = bookRepository.findById(1L).get();
//        bookRepository.delete(book2);

        Book book3 = bookRepository.findById(1L).get();
        book3.setPublisher(null);  // 연관관계 제거
        bookRepository.save(book3);

        publisherRepository.deleteById(1L);

        System.out.println("books3: " + bookRepository.findAll());
        System.out.println("publishers3: " + publisherRepository.findAll());
        System.out.println("book3-publisher3: " + bookRepository.findById(1L).get().getPublisher());
    }

    @Test
    void bookRemoveCascadeTest(){
        bookRepository.deleteById(1L);

        System.out.println("books: " + bookRepository.findAll());
        System.out.println("publishers: " + publisherRepository.findAll());

        bookRepository.findAll().forEach(book -> System.out.println(book.getPublisher()));

    }

    //where문 활용
    @Test
    void softDelete(){
        bookRepository.findAll().forEach(System.out::println);


//        bookRepository.findByCategoryIsNull().forEach(System.out::println);
//        bookRepository.findAllByDeletedFalse().forEach(System.out::println);
//        bookRepository.findByCategoryIsNullAndDeletedFalse().forEach(System.out::println);
    }

    @Test
    void queryTest(){
        bookRepository.findAll().forEach(System.out::println);

        System.out.println("findByCategoryIsNullAndNameEqualsAndCreateAtGreaterThanEqualAndUpdateAtGreaterThanEqual: " +
                bookRepository.findByCategoryIsNullAndNameEqualsAndCreateAtGreaterThanEqualAndUpdateAtGreaterThanEqual(
                        "JPA초격차",
                              LocalDateTime.now().minusDays(1L),
                              LocalDateTime.now().minusDays(1L)));

        System.out.println("findByNameRecently: " + bookRepository.findByNameRecently(
                "JPA초격차",
                LocalDateTime.now().minusDays(1L),
                LocalDateTime.now().minusDays(1L)));

        System.out.println("findBookNameAndCategory: " + bookRepository.findBookNameAndCategory());

        bookRepository.findBookNameAndCategory().forEach(b -> {
            System.out.println(b.getName() + ":" + b.getCategory());
        });

//        bookRepository.findBookNameAndCategory(PageRequest.of(1, 1)).forEach(
//                bookNameAndCategory -> System.out.println(bookNameAndCategory.getName() + " : " + bookNameAndCategory.getCategory())
//        );

    }

    @Test
    void nativeQueryTest(){
//        bookRepository.findAll().forEach(System.out::println);
//        bookRepository.findAllCustom().forEach(System.out::println);

        List<Book> books = bookRepository.findAll();

        for(Book book : books){
            book.setCategory("IT전문선");
        }

        bookRepository.saveAll(books);

        System.out.println(bookRepository.findAll());

        System.out.println("affected rows: " + bookRepository.updateCategories());
        bookRepository.findAllCustom().forEach(System.out::println);

        System.out.println(bookRepository.showTables());
    }

    @Test
    void converterTest(){
        bookRepository.findAll().forEach(System.out::println);

        Book book = new Book();
        book.setName("또다른 IT전문서적");
        book.setStatus(new BookStatus(200));

        bookRepository.save(book);

        System.out.println(bookRepository.findRawRecord().values());
    }

}
