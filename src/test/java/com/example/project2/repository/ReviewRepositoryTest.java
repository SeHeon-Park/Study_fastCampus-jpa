package com.example.project2.repository;

import com.example.project2.domain.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class ReviewRepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;


    /** N+1 이슈 **/  // N+1 문제: 데이터의 수만큼 조회하는 것(가독성 down)
    @Test
    @Transactional
    void reviewTest(){
        //review query는 1개인데 comment query는 2개(N개)임

//        (해결)
//        List<Review> reviews = reviewRepository.findAll();
//        List<Review> reviews = reviewRepository.findAllByFetchJoin();
        List<Review> reviews = reviewRepository.findAllByEntityGraph();


//        System.out.println(reviews);
//        System.out.println("전체를 가져 왔습니다.");
//
//        System.out.println(reviews.get(0).getComments());
//
//        System.out.println("첫번째 리뷰의 커멘트들을 가져왔습니다.");
//
//        System.out.println(reviews.get(1).getComments());
//
//        System.out.println("두번째 리뷰의 커멘트들을 가져왔습니다.");

        reviews.forEach(System.out::println);
    }
}
