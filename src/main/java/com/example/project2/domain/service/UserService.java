package com.example.project2.domain.service;

import com.example.project2.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class UserService {
    @Autowired
    private EntityManager entityManager;

    // 그림 그려보자!
    @Transactional
    public void putNotPersist(){   // 비영속
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@naver.com");
    }

    @Transactional
    public void putPersist(){      // 영속(managed 상태)
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@naver.com");

        entityManager.persist(user);

        user.setName("newUserAfterPersist");   // 이게 save안해도 db에 저장이 되네..
                                               // dirty check: 해당 스냅샷과 현재 entity 값을 비교후 update!
                                               // 스냅샷: 최초로 영속성 컨텍스트 1차 캐시에 들어온 상태 를 저장해두는 것
    }

    @Transactional
    public void putDetach(){   // 준영속(detached 상태: 원래 영속화 되어있는 객체를 분리해서 영속성 컨텍스트 밖으로 꺼내는 것)
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@naver.com");

        entityManager.persist(user);
        entityManager.detach(user);  // detach함수는  EntityManager만 존재

        user.setName("newUserAfterPersist");   // 이제 db에 저장 안되네(준영속이거든!)

        entityManager.merge(user);             // 다시 merge햇더니 저장되네!

        entityManager.clear();                 // 다시 안되네.. 하지만 그전에 flush()해주면 반영됨!
    }

    @Transactional
    public void putRemove(){   // 삭제(removed 상태: 더이상 사용x)
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@naver.com");

        entityManager.persist(user);
        user.setName("newUserAfterPersist");

        entityManager.remove(user);
    }

}
