package com.example.project2.domain.service;

import com.example.project2.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Test
    void test(){
        userService.putNotPersist();   // 비영속이라 put함수 끝나면 db에 저장되지 않고 garbage collector로
        System.out.println(">>> " + userRepository.findByEmail("newUser@naver.com"));

        System.out.println("-------------------------------------");

        userService.putPersist();
        System.out.println(">>> " + userRepository.findByEmail("newUser@naver.com"));

        System.out.println("-------------------------------------");

//        userService.putDetach();
//        System.out.println(">>> " + userRepository.findByEmail("newUser@naver.com"));
    }


}