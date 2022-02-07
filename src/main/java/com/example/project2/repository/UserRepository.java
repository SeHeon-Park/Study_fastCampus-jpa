package com.example.project2.repository;

import com.example.project2.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    List<User> findByName(String Name);   // findBy이 단어로 자동으로 완성해줌(Query method)
                                              // 인식하지 못한 키워드는 무시됨(오타 조심)
    /** 1) findBy, readBy, getBy, queryBy, searchBy, streamBy
           existsBy, countBy, deleteBy, removeBy
           First<number>, Top<number>, Distinct **/
    User findByEmail(String email);
    User readByEmail(String email);
    User getByEmail(String email);
    User queryByEmail(String email);
    User searchByEmail(String email);
    User streamByEmail(String email);
    User findUserByEmail(String Email);
    List<User> findFirst2ByName(String name);
    List<User> findTop2ByName(String name);

    /** 2) AND, OR .. (자료) **/
    List<User> findByEmailAndName(String email, String name);
    List<User> findByEmailOrName(String email, String name);
    List<User> findByCreateAtAfter(LocalDateTime yesterday);                           // (yesterday) < (쿼리 createAt) 만족하는 것 출력
    List<User> findByIdAfter(Long id);                                                 // (매개변수 id) < (쿼리 id)
    List<User> findByCreateAtGreaterThan(LocalDateTime yesterday);                     // (yesterday) < (쿼리 createAt)
    List<User> findByCreateAtGreaterThanEqual(LocalDateTime yesterday);                // (yesterday) <= (쿼리 createAt)
    List<User> findByCreateAtBetween(LocalDateTime yesterday, LocalDateTime tomorrow); // (yesterday) <= (쿼리 createAt) <= (tomorrow)
    List<User> findByIdBetween(Long id1, Long id2);                                    // Between == GreaterThanAndLessThanEqual

    /** 3) is, in **/
    List<User> findByIdIsNotNull();
    //List<User> findByAddressIsNotEmpty();
    List<User> findByNameIn(List<String> name);             // name똑같은 것 찾기? or랑 비슷
    List<User> findByNameStartingWith(String name);         // name으로 시작하는 것(ex-se)
    List<User> findByNameEndingWith(String name);           // name으로 끝나는 것
    List<User> findByNameContains(String name);             // name을 포함하고 있는 것
    List<User> findByNameLike(String name);                 // %se-se로 끝나는 / se%-se로 시작하는 / %se%-se를 포함하는

    /** 4) query method로 정렬시키기 **/
    List<User> findTop1ByNameOrderByIdAsc(String name);              // 정순 일때 첫번째 것    ※Top이랑 First 똑같아!
    List<User> findTop1ByNameOrderByIdDesc(String name);             // 역순 일때 첫번째 것
    List<User> findFirst2ByNameOrderByIdDescEmailAsc(String name);   // OrderBy는 And안쓰고 그냥 바로 연결해!
    List<User> findFirstByName(String name, Sort sort);              // Sort인자 가능

    /** 5) paging **/
    Page<User> findByName(String name, Pageable pageable);          // ※pageable: paging에 대한 요청값, page: paging에 대한 응답값

    /** enum 확인 **/
    @Query(value = "select * from user limit 1", nativeQuery = true) // limit: 제한하고 싶은 개수
    Map<String, Object> findRowRecord();  // 결과값이 Map에 저장

    @Query(value = "select * from user", nativeQuery = true)
    List<Map<String, Object>> findAllRawRecord();
}
