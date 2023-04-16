package com.lxl.xiaomi.service;

import com.lxl.xiaomi.entity.Cart;
import com.lxl.xiaomi.entity.Order;

import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.service
 * Description : OrderService
 * Author : LiuXinLei
 * createDate : 2023/4/13 19:42
 * Version : 1.0
 */
public interface OrderService {
    List<Order> queryList();

    boolean add(Order order, List<Cart> cartList);
}
