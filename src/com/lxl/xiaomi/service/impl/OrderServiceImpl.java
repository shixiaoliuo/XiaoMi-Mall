package com.lxl.xiaomi.service.impl;

import com.lxl.xiaomi.dao.OrderDao;
import com.lxl.xiaomi.dao.impl.OrderDaoImpl;
import com.lxl.xiaomi.entity.Cart;
import com.lxl.xiaomi.entity.Order;
import com.lxl.xiaomi.entity.OrderDetail;
import com.lxl.xiaomi.service.CartService;
import com.lxl.xiaomi.service.OrderDetailService;
import com.lxl.xiaomi.service.OrderService;
import com.lxl.xiaomi.utils.DruidUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.service.impl
 * Description : OrderServiceImpl
 * Author : LiuXinLei
 * createDate : 2023/4/13 19:43
 * Version : 1.0
 */
public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();

    @Override
    public List<Order> queryList() {
        return orderDao.selectAll();
    }


    @Override
    public boolean add(Order order, List<Cart> cartList) {
        try {
            DruidUtils.begin();
            orderDao.insert(order);
            OrderDetailService orderDetailService = new OrderDetailServiceImpl();
            for (Cart cart : cartList) {
                OrderDetail orderDetail = new OrderDetail(0, order.getId(), cart.getPid(), cart.getNum(), cart.getMoney());
                orderDetailService.add(orderDetail);
            }
            CartService cartService = new CartServiceImpl();
            cartService.delete(order.getUid());
//            int i = 10 / 0;
            DruidUtils.commit();
            return true;
        } catch (Exception e) {
            try {
                DruidUtils.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    DruidUtils.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            return false;
        }

    }

    @Override
    public Order queryById(String oid) {
        return orderDao.selectById(oid);
    }

    @Override
    public boolean updateStatus(Order order) {
        int i = orderDao.updateStatus(order);
        if (i > 0) {
            return true;
        }
        return false;
    }
}
