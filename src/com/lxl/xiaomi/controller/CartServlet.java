package com.lxl.xiaomi.controller;

import com.lxl.xiaomi.entity.CartDto;
import com.lxl.xiaomi.entity.User;
import com.lxl.xiaomi.service.CartDtoService;
import com.lxl.xiaomi.service.CartService;
import com.lxl.xiaomi.service.impl.CartDtoServiceImpl;
import com.lxl.xiaomi.service.impl.CartServiceImpl;
import com.lxl.xiaomi.utils.StringUtils;

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
    CartDtoService cartDtoService = new CartDtoServiceImpl();

    public String getCart(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        Integer id = user.getId();
        List<CartDto> cart = cartDtoService.queryMap(id);
//        System.out.println(cart);
        req.getSession().setAttribute("cart", cart);
        return "redirect:/cart.jsp";
    }

    //&goodsId=2&number=1
    public String addCart(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Integer goodsId = Integer.valueOf(req.getParameter("goodsId"));
        Integer number = Integer.valueOf(req.getParameter("number"));

        CartService cartService = new CartServiceImpl();
        User user = (User) req.getSession().getAttribute("user");
        Integer id = user.getId();
        if (cartService.add(id, goodsId, number)) {
            return "redirect:/cartSuccess.jsp";
        }
        return "redirect:/goodsDetail.jsp";
    }

    //    addCartAjax&goodsId=6&num=0
    public void addCartAjax(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String goodId = req.getParameter("goodsId");
        String num = req.getParameter("num");
        User user = (User) req.getSession().getAttribute("user");
        Integer id = user.getId();
        if (!StringUtils.isEmpty(goodId) && !StringUtils.isEmpty("num")) {
            if ("-1".equals(num)) {
                //num--
                cartDtoService.removeNum(id, Integer.valueOf(goodId), Integer.valueOf(num));
            } else if ("0".equals(num)) {
                cartDtoService.remove(id, Integer.valueOf(goodId));
            } else if ("1".equals(num)) {
                cartDtoService.addNum(id, Integer.valueOf(goodId));
            }
        }
    }

    public void clearCartAjax(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        Integer id = user.getId();
        cartDtoService.clearAll(id);
    }
}
