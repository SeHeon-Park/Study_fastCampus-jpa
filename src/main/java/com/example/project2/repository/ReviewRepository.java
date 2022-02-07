package com.example.project2.repository;

import com.example.project2.domain.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // n+1 이슈 해결 첫번째 방법
    @Query("select distinct r from Review r join fetch r.comments")
    List<Review> findAllByFetchJoin();

    // n+1 이슈 해결 두번째 방법
    @EntityGraph(attributePaths = "comments")
    @Query("select r from Review r")
    List<Review> findAllByEntityGraph();

    @EntityGraph(attributePaths = "comments")
    List<Review> findAll();
}
