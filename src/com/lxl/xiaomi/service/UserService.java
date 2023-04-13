package com.lxl.xiaomi.service;

import com.lxl.xiaomi.entity.User;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.service
 * Description : UserService
 * Author : LiuXinLei
 * createDate : 2023/4/13 9:51
 * Version : 1.0
 */
public interface UserService {
    boolean add(User user);

    Integer queryCountByName(String username);

    User login(String username, String password);
}
