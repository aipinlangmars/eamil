package com.mars.wang.dao;

import com.mars.wang.domain.User;

public interface UserDao {

    void insertUser(User user);

    User login(User user);
}
