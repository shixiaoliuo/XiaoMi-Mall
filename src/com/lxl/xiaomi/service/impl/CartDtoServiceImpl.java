package com.lxl.xiaomi.service.impl;

import com.lxl.xiaomi.dao.CartDao;
import com.lxl.xiaomi.dao.GoodsDao;
import com.lxl.xiaomi.dao.impl.CartDaoImpl;
import com.lxl.xiaomi.dao.impl.GoodsDaoImpl;
import com.lxl.xiaomi.entity.Cart;
import com.lxl.xiaomi.entity.CartDto;
import com.lxl.xiaomi.entity.Goods;
import com.lxl.xiaomi.service.CartDtoService;

import java.util.ArrayList;
import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.service.impl
 * Description : CartDtoServiceImpl
 * Author : LiuXinLei
 * createDate : 2023/4/14 14:21
 * Version : 1.0
 */
public class CartDtoServiceImpl implements CartDtoService {
    CartDao cartDao = new CartDaoImpl();
    GoodsDao goodsDao = new GoodsDaoImpl();

    @Override
    public List<CartDto> queryMap(Integer id) {

        List<Cart> carts = cartDao.selectById(id);
        List<CartDto> cartDtos = new ArrayList<>();

        for (Cart cart : carts) {
            Goods goods = goodsDao.selectById(cart.getPid());
            CartDto cartDto = new CartDto(cart.getId(), cart.getPid(), cart.getNum(), cart.getMoney(), goods);
            cartDtos.add(cartDto);
        }
        return cartDtos;
    }
}
