package com.lxl.xiaomi.service;

import com.lxl.xiaomi.entity.Cart;

import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.service
 * Description : CartService
 * Author : LiuXinLei
 * createDate : 2023/4/14 10:21
 * Version : 1.0
 */
public interface CartService {
    List<Cart> queryList();

    boolean add(Integer id, Integer goodsId, Integer number);

//    Map<Car> queryMap();
}
