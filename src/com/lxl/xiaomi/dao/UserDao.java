package com.lxl.xiaomi.dao;

import com.lxl.xiaomi.entity.User;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.dao
 * Description : UserDao
 * Author : LiuXinLei
 * createDate : 2023/4/13 9:55
 * Version : 1.0
 */
public interface UserDao {
    boolean insert(User user);

    User selectOneByName(String username);

    User selectOneByNameAndPwd(String username, String password);
}
