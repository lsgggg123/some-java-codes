package com.lsgggg123.h2.service;

import com.lsgggg123.h2.dao.UserDao;
import com.lsgggg123.h2.mode.User;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @PostConstruct
    public void init() {
        System.out.println(userDao);
        System.out.println(transactionTemplate);
    }

    public void update(int outer, int inner, int input) {
        transactionTemplate.setPropagationBehavior(outer);
        transactionTemplate.execute(status1 -> {
            User user1 = userDao.getById(1L);
            user1.setAge(user1.getAge() + 1);
            userDao.update(user1);

            if (input == 1) {
                throw new RuntimeException("biz error1");
            }

            User user2 = userDao.getById(2L);
            user2.setAge(user2.getAge() + 1);
            userDao.update(user2);
            
            transactionTemplate.setPropagationBehavior(inner);
            try {
                transactionTemplate.execute(status2 -> {
                    User user3 = userDao.getById(3L);
                    user3.setAge(user3.getAge() + 1);
                    userDao.update(user3);

                    if (input == 2) {
                        throw new RuntimeException("biz error2");
                    }

                    User user4 = userDao.getById(4L);
                    user4.setAge(user4.getAge() + 1);
                    userDao.update(user4);

                    return 2;
                });
            } catch (Exception e) {
                LOGGER.error("inner error", e);
            }

            if (input == 3) {
                throw new RuntimeException("biz error3");
            }


            return 1;
        });
    }

    public void reset() {

    }
}
