package com.mars.wang.service.impl;

import com.mars.wang.MyException;
import com.mars.wang.dao.UserDao;
import com.mars.wang.domain.User;
import com.mars.wang.service.UserService;
import com.mars.wang.utils.SqlSessionUtil;

public class UserServiceImpl implements UserService {

    @Override
    public User login(User user) throws MyException {
        UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

        User login=null;

            login = userDao.login(user);
            if(login==null){

                throw new MyException("账户密码错误！");
            }
            return login;





    }


}
