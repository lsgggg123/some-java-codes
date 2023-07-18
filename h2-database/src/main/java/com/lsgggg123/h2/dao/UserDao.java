package com.lsgggg123.h2.dao;

import com.lsgggg123.h2.mode.User;

/**
 * user dao demo
 */
public interface UserDao {
    /**
     * select user by id
     *
     * @param id user id
     * @return User
     */
    User getById(Long id);

    /**
     * add user
     *
     * @param user User
     * @return primary key
     */
    long add(User user);

    /**
     * update user
     *
     * @param user User
     * @return row affected
     */
    int update(User user);

    /**
     * delete user by id
     *
     * @param id user id
     * @return row affected
     */
    int deleteById(Long id);
}
