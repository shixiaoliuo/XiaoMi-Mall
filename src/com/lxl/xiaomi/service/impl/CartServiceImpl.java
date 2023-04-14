package com.lxl.xiaomi.service.impl;

import com.lxl.xiaomi.dao.CartDao;
import com.lxl.xiaomi.dao.impl.CartDaoImpl;
import com.lxl.xiaomi.entity.Cart;
import com.lxl.xiaomi.service.CartService;

import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.service.impl
 * Description : CartServiceImpl
 * Author : LiuXinLei
 * createDate : 2023/4/14 10:21
 * Version : 1.0
 */
public class CartServiceImpl implements CartService {
    CartDao cartDao = new CartDaoImpl();
    @Override
    public List<Cart> queryList() {
        return cartDao.selectAll();
    }
}
