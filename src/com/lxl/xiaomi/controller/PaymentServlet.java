package com.lxl.xiaomi.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.controller
 * Description : PaymentServlet
 * Author : LiuXinLei
 * createDate : 2023/4/15 16:37
 * Version : 1.0
 */
@WebServlet(name = "PaymentServlet", urlPatterns = "/payment")
public class PaymentServlet extends BaseServlet {


    //payment/weixinpay?orderId=1096836332132499456&price=2499.00&body=%E5%B0%8F%E7%B1%B36&url=http%3A%2F%2Flocalhost%3A8080%2Fxiaomi2%2FpaySuccess
    public void weixinpay(HttpServletRequest req, HttpServletResponse resp) {
        String orderId = req.getParameter("orderId");
        String price = req.getParameter("price");


    }
}
