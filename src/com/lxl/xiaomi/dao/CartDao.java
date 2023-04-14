package com.lxl.xiaomi.dao;

import com.lxl.xiaomi.entity.Cart;

import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.dao
 * Description : CartDao
 * Author : LiuXinLei
 * createDate : 2023/4/14 10:23
 * Version : 1.0
 */
public interface CartDao {
    List<Cart> selectAll();

    List<Cart> selectById(Integer id);

    int insert(Cart cart);

    Cart selectOne(Integer id,Integer pid);

    int update(Cart cart);
}
