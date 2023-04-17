<%--
  Created by IntelliJ IDEA.
  User: LiuXinLei
  Date: 2023/4/17
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.alipay.api.internal.util.AlipaySignature" %>
<%@ page import="com.lxl.xiaomi.config.AlipayConfig" %>
<%@ page import="com.lxl.xiaomi.service.OrderService" %>
<%@ page import="com.lxl.xiaomi.service.impl.OrderServiceImpl" %>
<%@ page import="com.lxl.xiaomi.entity.Order" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="js/jquery.min.js"></script>

    <link rel="stylesheet" type="text/css" href="css/login.css">
    <title>支付结果</title>
</head>
<body>
<div class="regist">
    <div class="regist_center">
        <div class="regist_top">
            <div class="left fl"><span class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;支付结果</div>
            <div class="right fr">
                <a href="index.jsp" target="_black">小米商城</a>
            </div>
            <div class="clear"></div>
            <div class="xian center"></div>
        </div>
        <div style="margin-top: 80px;margin-left: 100px;">

            <%
                System.out.println("=========支付宝同步重定向========");
                Map<String, String> params = new HashMap<>();
                Map<String, String[]> requestParams = request.getParameterMap();
                for (String name : requestParams.keySet()) {
                    params.put(name, request.getParameter(name));
                    //System.out.println(name+"---"+request.getParameter(name));
                }
                String sign = params.get("sign");
                String content = AlipaySignature.getSignCheckContentV1(params);
                boolean checkSignature = AlipaySignature.rsa256CheckContent(content, sign, AlipayConfig.alipayPublicKey, "UTF-8"); // 验证签名
                pageContext.setAttribute("checkSignature", checkSignature);
                if (checkSignature) {
                    System.out.println("商户订单号: " + params.get("out_trade_no"));
                    System.out.println("交易金额: " + params.get("total_amount"));
                    System.out.println("买家付款时间: " + params.get("timestamp"));

                    OrderService orderService = new OrderServiceImpl();
                    Order order = orderService.queryById(params.get("out_trade_no"));
                    order.setStatus("2");
                    orderService.updateStatus(order);
                }
            %>
            <c:if test="${checkSignature}">
                <p class="text-info" style="font-size: 20px;">${param.out_trade_no}支付成功...</p>
            </c:if>
            <c:if test="${!checkSignature}">
                <p class="text-info" style="font-size: 20px;">${param.out_trade_no}支付失败...</p>
            </c:if>
            <a class="btn btn-default" href="${pageContext.request.contextPath }/index.jsp" target="_blank">返回主页</a>

        </div>
    </div>
</div>
</body>
</html>
