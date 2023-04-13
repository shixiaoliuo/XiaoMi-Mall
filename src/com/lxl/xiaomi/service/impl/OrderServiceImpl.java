package com.lxl.xiaomi.service.impl;

import com.lxl.xiaomi.dao.OrderDao;
import com.lxl.xiaomi.dao.impl.OrderDaoImpl;
import com.lxl.xiaomi.entity.Order;
import com.lxl.xiaomi.service.OrderService;

import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.service.impl
 * Description : OrderServiceImpl
 * Author : LiuXinLei
 * createDate : 2023/4/13 19:43
 * Version : 1.0
 */public class OrderServiceImpl implements OrderService {
     OrderDao orderDao = new OrderDaoImpl();
    @Override
    public List<Order> queryList() {
        return orderDao.selectAll();
    }
}
