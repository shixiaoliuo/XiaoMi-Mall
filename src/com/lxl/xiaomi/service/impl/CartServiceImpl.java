package com.lxl.xiaomi.service.impl;

import com.lxl.xiaomi.dao.CartDao;
import com.lxl.xiaomi.dao.GoodsDao;
import com.lxl.xiaomi.dao.impl.CartDaoImpl;
import com.lxl.xiaomi.dao.impl.GoodsDaoImpl;
import com.lxl.xiaomi.entity.Cart;
import com.lxl.xiaomi.entity.Goods;
import com.lxl.xiaomi.service.CartService;

import java.math.BigDecimal;
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
    GoodsDao goodsDao = new GoodsDaoImpl();

    @Override
    public List<Cart> queryList() {
        return cartDao.selectAll();
    }

    @Override
    public boolean add(Integer id, Integer goodsId, Integer number) {
        Goods goods = goodsDao.selectById(goodsId);
        BigDecimal price = goods.getPrice().multiply(new BigDecimal(String.valueOf(number)));
        Cart cart = new Cart(id, goodsId, number, price);
        Cart cart1 = cartDao.selectOne(id, goodsId);
        if (cart1 == null) {
            int updateCount = cartDao.insert(cart);
            if (updateCount > 0) {
                return true;
            }
        } else {
            int updateCount = cartDao.update(cart);
            if (updateCount > 0) {
                return true;
            }
        }
        return false;
    }
}
