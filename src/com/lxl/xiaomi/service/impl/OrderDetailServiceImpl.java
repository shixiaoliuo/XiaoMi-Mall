package com.lxl.xiaomi.service.impl;

import com.lxl.xiaomi.dao.OrderDetailDao;
import com.lxl.xiaomi.dao.impl.OrderDetailDaoImpl;
import com.lxl.xiaomi.entity.OrderDetail;
import com.lxl.xiaomi.service.OrderDetailService;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.service.impl
 * Description : OrderDetailServiceImpl
 * Author : LiuXinLei
 * createDate : 2023/4/15 12:06
 * Version : 1.0
 */
public class OrderDetailServiceImpl implements OrderDetailService {
    OrderDetailDao orderDetailDao = new OrderDetailDaoImpl();

    @Override
    public boolean add(OrderDetail orderDetail) {
        int i = orderDetailDao.insert(orderDetail);
        if (i > 0) {
            return true;
        }
        return false;
    }
}
