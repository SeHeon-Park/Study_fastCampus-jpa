package com.example.project2.domain.listener;


import com.example.project2.domain.User;
import com.example.project2.domain.UserHistory;
import com.example.project2.repository.UserHistoryRepository;
import com.example.project2.repository.UserRepository;
import com.example.project2.support.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


public class UserEntityListener {

    @PostPersist
    @PostUpdate
    public void prePersistAndPreUpdate(Object o){

        // Entity Listener는 spring Bean을 주입받지 못함, 다음과 같이 Bean을 가져와야해!
        // 새로운 클래스 만들어서 implements ApplicationContextAware
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);
        User user = (User) o;
        UserHistory userHistory = new UserHistory();
        userHistory.setName(user.getName());
        userHistory.setUser(user);
        userHistory.setEmail(user.getEmail());

        userHistoryRepository.save(userHistory);
    }
}
