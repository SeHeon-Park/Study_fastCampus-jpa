package com.example.project2.repository;

import com.example.project2.domain.Address;
import com.example.project2.domain.Gender;
import com.example.project2.domain.User;
import com.example.project2.domain.UserHistory;
import jdk.swing.interop.SwingInterOpUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AbstractSoftAssertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
@Transactional  // 각 테스트 메소드가 종료 종료 될떄마다 처리했던 데이터를 모두 롤백
class UserRepositoryTest {
    @Autowired   // 의존성 추가
    private UserRepository userRepository;
    @Autowired
    private UserHistoryRepository userHistoryRepository;
    @Autowired
    private EntityManager entityManager;

    /** ch2-Repository Interface method **/
    @Test
    @Transactional
    void crud(){

        /** save **/
        /*List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
        List<User> users = userRepository.findAllById(Lists.newArrayList(1L, 3L, 5L));
        User user1 = new User("steve", "quwe153");
        User user2 = new User("Park", "sonamu0928");
        userRepository.saveAll(Lists.newArrayList(user1, user2));
        var user = userRepository.getById(1L);
        System.out.println(user);*/

        /** count, exists **/
        /*userRepository.saveAndFlush(new User("seheon", "quwe153@naver.com"));
        long count = userRepository.count();
        System.out.println(count);
        boolean exists = userRepository.existsById(2L);
        System.out.println(exists);*/

        /** delete **/
        /*userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
        userRepository.deleteById(1L);
        userRepository.deleteAll();
        userRepository.deleteAll(userRepository.findAllById(Lists.newArrayList(1L, 3L)));
        userRepository.deleteAllByIdInBatch(Lists.newArrayList(1L, 2L));*/      // Batch 써있는 delete는 select로 확인하는 과정이 없어 효과적

        /** paging **/
        /*Page<User> userPage = userRepository.findAll(PageRequest.of(0, 3));   // 매개변수(page "0"부터,  페이지당 데이터 개수 "3")
                                                                                // 결국 page0에 3개 page1에 2개들어감
        System.out.println("page: " + userPage);
        System.out.println("전체수: " + userPage.getTotalElements());
        System.out.println("페이지수: "+ userPage.getTotalPages());  // size3으로 해서 5개를 페이지 하나당 3개로 나눔, 따라서 2
        System.out.println("현재 가져온 record수: " + userPage.getNumberOfElements());
        System.out.println("sort: " + userPage.getSort());
        System.out.println("size(페이지를 할때 나눈 크기): " + userPage.getSize());
        System.out.println("내부 유저 정보: ");
        userPage.getContent().forEach(System.out::println);*/

        /** match, example **/
        /*ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name")                        //  name은 무시
                .withMatcher("email", endsWith());              //  email은 확인   ※endsWith()는 완벽하게 똑같아야함

        Example<User> example = Example.of(new User("ma", "1@naver.com"), matcher);  // 해당 내용과 일치한것 골라냄
        userRepository.findAll(example).forEach(System.out::println);

        User u = new User();
        u.setEmail("naver");
        ExampleMatcher matcher1 = ExampleMatcher.matching()
                .withMatcher("email", contains());               // ※contains()는 일부만 있어도 됨
        Example<User> example1 = Example.of(u, matcher1);
        userRepository.findAll(example1).forEach(System.out::println);*/

    }


    /** ch3-Query method **/
    @Test
    void select(){

        /**1**/
        /*System.out.println(userRepository.findByName("hyontack"));
        System.out.println("findByEmail: " + userRepository.findByEmail("1@naver.com"));
        System.out.println("readByEmail: " + userRepository.readByEmail("1@naver.com"));
        System.out.println("getByEmail: " + userRepository.getByEmail("1@naver.com"));
        System.out.println("queryByEmail: " + userRepository.queryByEmail("1@naver.com"));
        System.out.println("searchByEmail: " + userRepository.searchByEmail("1@naver.com"));
        System.out.println("streamByEmail: " + userRepository.streamByEmail("1@naver.com"));
        System.out.println("findUserByEmail: " + userRepository.findUserByEmail("1@naver.com"));
        System.out.println("findFirstByName: " + userRepository.findFirst2ByName("seheon"));
        System.out.println("findTopByName: " + userRepository.findTop2ByName("seheon"));*/

        /**2**/
        /*System.out.println("findByEmailAndName: " + userRepository.findByEmailAndName("1@naver.com", "seheon"));
        System.out.println("findByEmailOrName: " + userRepository.findByEmailOrName("1@naver.com", "seheon"));
        System.out.println("findByCreateAtAfter: " + userRepository.findByCreateAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByIdAfter: " + userRepository.findByIdAfter(2L));
        System.out.println("findByCreateAtGreaterThan: " + userRepository.findByCreateAtGreaterThan(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreateAtGreaterThanEqual: " + userRepository.findByCreateAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreateAtBetween: " + userRepository.findByCreateAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L)));
        System.out.println("findByIdBetween: " + userRepository.findByIdBetween(2l, 4l));*/

        /**3**/
        /*System.out.println("findByIdIsNotNull: " + userRepository.findByIdIsNotNull());
        System.out.println("findByAddressIsNotEmpty: " + userRepository.findByAddressIsNotEmpty());
        System.out.println("findByNameIn: " + userRepository.findByNameIn(Lists.newArrayList("seheon", "sonhyon")));
        System.out.println("findByNameStartingWith: " + userRepository.findByNameStartingWith("se"));
        System.out.println("findByNameEndingWith: " + userRepository.findByNameEndingWith("on"));
        System.out.println("findByNameContains: " + userRepository.findByNameContains("on"));
        System.out.println("findByNameLike: " + userRepository.findByNameLike("%on%"));*/

        /**4**/
        /*System.out.println("findTop1ByNameOrderByIdDesc: " + userRepository.findTop1ByNameOrderByIdDesc("seheon"));
        System.out.println("findTop1ByNameOrderByIdAsc: " + userRepository.findTop1ByNameOrderByIdAsc("seheon"));
        System.out.println("findFirstByNameOrderByIdDescEmailAsc: " + userRepository.findFirst2ByNameOrderByIdDescEmailAsc("seheon"));
        System.out.println("findFirstByNameWithSortParams: " + userRepository.findFirstByName("seheon", Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email"))));  // 따로 함수 만들어서 가독성 좋게 할 수 있음*/


        /**5**/
        /*System.out.println("findByNameWithPaging: " + userRepository.findByName("seheon", PageRequest.of(1, 1, Sort.by(Sort.Order.desc("id")))).getTotalElements());*/

    }

    /** ch04-Entity 기본속성 **/
    @Test
    void insertAndUpdateTest(){
        User user = new User();

        user.setName("seheon");
        user.setEmail("12@naver.com");

        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("seheon22");

        userRepository.save(user2);

    }

    @Test
    void enumTest(){
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);

        userRepository.save(user);
        userRepository.findAll().forEach(System.out::println);

        System.out.println(userRepository.findRowRecord().get("gender"));
    }

    @Test
    void Test(){
        userRepository.save(new User());
        userRepository.findAll().forEach(System.out::println);
    }

    /** ch05-listener 활용 **/
    @Test
    void listenerTest(){
        User user = new User();
        user.setEmail("123@naver.com");
        user.setName("seheon33");

        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("seeeeeeon");

        userRepository.save(user2);

        userRepository.deleteById(4L);
    }

    @Test
    void prePersistTest(){
        User user = new User();
        user.setEmail("22@naver.com");
        user.setName("seheon");
//        user.setCreateAt(LocalDateTime.now());
//        user.setUpdateAt(LocalDateTime.now());

        userRepository.save(user);

        System.out.println(userRepository.findByEmail("22@naver.com"));
    }

    @Test
    void preUpdateTest(){
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);

        System.out.println("as-is: " + user);
        user.setName("parkseheon");
        userRepository.save(user);

        System.out.println("to-be: " + userRepository.findAll().get(0));
    }

    @Test
    void userHistoryTest(){
        User user = new User();
        user.setName("seheonpark");
        user.setEmail("quwe153@naver.com");

        userRepository.save(user);
        user.setName("parkseheon");
        userRepository.save(user);
        userRepository.findAll().forEach(System.out::println);

        userHistoryRepository.findAll().forEach(System.out::println);

    }

    /** 1대N 관계 **/
    @Test
    void userRelationTest(){
        User user = new User();
        user.setName("David");
        user.setEmail("David@naver.com");
        user.setGender(Gender.MALE);
        userRepository.save(user);

        user.setName("Daniel");
        userRepository.save(user);

        user.setEmail("Daniel@naver.com");
        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);
//        List<UserHistory> result = userHistoryRepository
//                .findByUserId(userRepository.findByEmail("Daniel@naver.com").getId());
        List<UserHistory> result = userRepository.findByEmail("Daniel@naver.com").getUserHistories();
        result.forEach(System.out::println);
        System.out.println("UserHistory.getUser(): " + userHistoryRepository.findAll().get(0).getUser());
    }

    /** ch10-임베디드 타입 활용 **/
    @Test
    void embedTest(){
        userRepository.findAll().forEach(System.out::println);

        User user = new User();
        user.setName("steve");
        user.setHomeAddress(new Address("서울시", "강남구", "강남대로 364 미왕빌딩", "06214"));
        user.setCompanyAddress((new Address("서울시", "성동구", "성수이로 113 제강빌딩", "04794")));

        userRepository.save(user);

        User user1 = new User();
        user1.setName("Joshua");
        user1.setHomeAddress(null);            // Address자체가 null(이것도 근데 결국 안에도 null)
        user1.setCompanyAddress(null);

        userRepository.save(user1);

        User user2 = new User();
        user2.setName("Jordan");
        user2.setHomeAddress(new Address());    // Address안 내용 각각이 null
        user2.setCompanyAddress(new Address());

        userRepository.save(user2);

        entityManager.clear();

        userRepository.findAll().forEach(System.out::println);
        userHistoryRepository.findAll().forEach(System.out::println);

        userRepository.findAllRawRecord().forEach(a -> System.out.println(a.values()));

//        assertAll(
//                () -> assertThat(userRepository.findById(7L).get().getHomeAddress()).isNull(),
//                () -> assertThat(userRepository.findById(8L).get().getHomeAddress()).isInstanceOf(Address.class)
//        );
    }

}