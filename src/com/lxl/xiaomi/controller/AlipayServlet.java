package com.lxl.xiaomi.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.lxl.xiaomi.config.AlipayConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.controller
 * Description : AlipayServlet
 * Author : LiuXinLei
 * createDate : 2023/4/17 11:12
 * Version : 1.0
 */
@WebServlet(name = "AlipayServlet", urlPatterns = "/alipay")
public class AlipayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.创建阿里客户端对象
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.GATEWAY_URL, AlipayConfig.appId,
                AlipayConfig.appPrivateKey, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.alipayPublicKey, AlipayConfig.SIGN_TYPE);

        // 2. 创建 payRequest并设置payRequest参数
        AlipayTradePagePayRequest payRequest = new AlipayTradePagePayRequest();  // 发送请求的 Request类
        payRequest.setNotifyUrl(AlipayConfig.notifyUrl);
        payRequest.setReturnUrl(AlipayConfig.returnUrl);
        JSONObject bizContent = new JSONObject();

        String orderId = request.getParameter("oid");
        String money = request.getParameter("money");

        bizContent.put("out_trade_no", orderId);  // 我们自己生成的订单编号
        bizContent.put("total_amount", Double.parseDouble(money)); // 订单的总金额
        bizContent.put("subject", "phone");   // 支付的名称
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");  // 固定配置
        payRequest.setBizContent(bizContent.toString());

        //3执行请求，拿到响应的结果，返回给浏览器
        String form = "";
        try {
            form = alipayClient.pageExecute(payRequest).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
        response.getWriter().write(form);// 直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
