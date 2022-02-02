package com.example.project2.service;

import com.example.project2.domain.User;
import com.example.project2.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional   // 영속성 컨텍스트내에서 entity manager가 자체적으로 entity상태를 merge하고 최종적으로 db에 반영해야되는 상태만 실행이됨(따라서 이걸 안쓰면 delete가 db에 반영이 안되어 consele창에 안나옴)
                 // merge되어 한번에 보내기 때문에 update문이 한번만 시행
                 // 여기서 Transactional을 여기서 묶지 않으면 save함수 각각만 Transactional됨
public class EntityManagerTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    /** ch07-영속성 **/
    @Test
    void entityManagerTest(){
        System.out.println(entityManager.createQuery("select u from User u").getResultList());  // userRepository.findAll과 같음
    }

    @Test
    void cacheFindTest(){   // entity cache 활용, 1차 캐시=>map형태(key: id, value: entity)
        System.out.println(userRepository.findByEmail("1@naver.com"));  // select하고 출력하고, select하고 출력하고...
        System.out.println(userRepository.findByEmail("1@naver.com"));
        System.out.println(userRepository.findByEmail("1@naver.com"));

        System.out.println(userRepository.findById(2L).get());          // 한번 select하면 cache에 저장되어있어 나머지 바로 출력
        System.out.println(userRepository.findById(2L).get());
        System.out.println(userRepository.findById(2L).get());
    }

    @Test
    void cacheDindTest2(){
        User user = userRepository.findById(1L).get();
        user.setName("passs");

        userRepository.save(user);

        System.out.println("----------------");

        user.setEmail("passs@naver.com");

        userRepository.save(user);

        userRepository.flush();

        System.out.println("1>>>" + userRepository.findById(1L).get());
        System.out.println("2>>>" + userRepository.findById(1L).get());

    }

    //비영속 상태: 영속성 컨텍스트가 해당 entity객체를 관리하지 않는 상태(@Transient)(단순한 자바object 취급)

}
