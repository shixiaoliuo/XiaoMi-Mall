package com.lxl.xiaomi.dao;

import com.lxl.xiaomi.entity.Order;

import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.dao
 * Description : OrderDao
 * Author : LiuXinLei
 * createDate : 2023/4/13 19:48
 * Version : 1.0
 */
public interface OrderDao {
    List<Order> selectAll();
}
