package com.mars.wang.service;

import com.mars.wang.MyException;
import com.mars.wang.domain.User;

public interface UserService {


    User login(User user) throws MyException;

}
