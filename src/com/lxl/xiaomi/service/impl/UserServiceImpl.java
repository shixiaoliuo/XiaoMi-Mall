package com.lxl.xiaomi.service.impl;

import com.lxl.xiaomi.dao.UserDao;
import com.lxl.xiaomi.dao.impl.UserDaoImpl;
import com.lxl.xiaomi.entity.User;
import com.lxl.xiaomi.service.UserService;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.service.impl
 * Description : UserServiceImpl
 * Author : LiuXinLei
 * createDate : 2023/4/13 9:52
 * Version : 1.0
 */
public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public boolean add(User user) {
        return userDao.insert(user);
    }

    @Override
    public Integer queryCountByName(String username) {
        User user = userDao.selectOneByName(username);
        if (user != null) {
            return 1;
        }
        return null;
    }

    @Override
    public User login(String username, String password) {
        return userDao.selectOneByNameAndPwd(username, password);
    }
}
