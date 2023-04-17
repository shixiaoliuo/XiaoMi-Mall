package com.lxl.xiaomi.controller;

import com.alibaba.fastjson.JSON;
import com.lxl.xiaomi.entity.Order;
import com.lxl.xiaomi.entity.WxResult;
import com.lxl.xiaomi.service.OrderService;
import com.lxl.xiaomi.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneOffset;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.controller
 * Description : PaymentServlet
 * Author : LiuXinLei
 * createDate : 2023/4/15 16:37
 * Version : 1.0
 */
@WebServlet(name = "PaySuccessServlet", urlPatterns = "/paySuccess")
public class PaySuccessServlet extends HttpServlet {


    //payment/weixinpay?orderId=1096836332132499456&price=2499.00&body=%E5%B0%8F%E7%B1%B36&url=http%3A%2F%2Flocalhost%3A8080%2Fxiaomi2%2FpaySuccess

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = req.getParameter("result");
        try {

            WxResult wxResult = JSON.parseObject(json, WxResult.class);
            String oid = wxResult.getResult().getOut_trade_no();
            if (wxResult.getResult().getResult_code().equalsIgnoreCase("SUCCESS")) {
                OrderService orderService = new OrderServiceImpl();
                Order order = orderService.queryById(oid);
                order.setStatus("2");
                orderService.updateStatus(order);

                req.setAttribute("msg", oid + "支付成功，请等待发货。。。");
            } else {
                req.setAttribute("msg", oid + "支付失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", "支付失败：" + e.getMessage());
        }
        req.getRequestDispatcher("/message.jsp").forward(req, resp);
    }
}
