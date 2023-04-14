package com.lxl.xiaomi.controller;

import com.lxl.xiaomi.entity.Cart;
import com.lxl.xiaomi.service.CartService;
import com.lxl.xiaomi.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.controller
 * Description : CartServlet
 * Author : LiuXinLei
 * createDate : 2023/4/14 10:11
 * Version : 1.0
 */
@WebServlet(name = "CartServlet", urlPatterns = "/cartservlet")
public class CartServlet extends BaseServlet {
    CartService cartService = new CartServiceImpl();
    public String getCart(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Cart> carts = cartService.queryList();

        return "";
    }
}
