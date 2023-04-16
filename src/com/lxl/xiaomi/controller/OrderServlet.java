package com.lxl.xiaomi.controller;

import com.lxl.xiaomi.entity.Address;
import com.lxl.xiaomi.entity.Cart;
import com.lxl.xiaomi.entity.Order;
import com.lxl.xiaomi.entity.User;
import com.lxl.xiaomi.service.AddressService;
import com.lxl.xiaomi.service.CartService;
import com.lxl.xiaomi.service.OrderService;
import com.lxl.xiaomi.service.impl.AddressServiceImpl;
import com.lxl.xiaomi.service.impl.CartServiceImpl;
import com.lxl.xiaomi.service.impl.OrderServiceImpl;
import com.lxl.xiaomi.utils.SnowflakeIdWorker;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.controller
 * Description : OrderServlet
 * Author : LiuXinLei
 * createDate : 2023/4/14 22:13
 * Version : 1.0
 */
@WebServlet(name = "OrderServlet", urlPatterns = "/orderservlet")
public class OrderServlet extends BaseServlet {
    //orderservlet?method=getOrderView
    public String getOrderView(HttpServletRequest req, HttpServletResponse resp) {
        //1 判断当前是否已经登录
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login.jsp";
        }
        try {
            //2查询购物车信息
            CartService cartService = new CartServiceImpl();
            List<Cart> cartList = cartService.queryByUid(user.getId());

            //3查询收货地址
            AddressService addressService = new AddressServiceImpl();
            List<Address> addList = addressService.queryByUid(user.getId());

            //4放入域中
            req.setAttribute("cartList", cartList);
            req.setAttribute("addList", addList);
            //5转发
            return "/order.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", "结账预览失败");
            return "/message.jsp";
        }
    }

    //orderservlet?method=addOrder&aid=12
    public String addOrder(HttpServletRequest request, HttpServletResponse response) {
        //1 判断当前是否已经登录
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login.jsp";
        }
        //2 获取参数
        String aid = request.getParameter("aid");

        //3创建业务对象
        CartService cartService = new CartServiceImpl();
        List<Cart> cartList = cartService.queryByUid(user.getId());
        if (cartList == null) {
            request.setAttribute("msg", "购物车为空");
            return "/message.jsp";
        }


        OrderService orderService = new OrderServiceImpl();

        try {
            //生成订单号
            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(1, 1);

            String orderId = String.valueOf(idWorker.generateId());
            //遍历购物车
            BigDecimal sum = new BigDecimal(0);
            for (Cart cart : cartList) {
                sum = sum.add(cart.getMoney());
            }
            Order order = new Order(orderId, user.getId(), sum, "1", new Date(), Integer.parseInt(aid));

            orderService.add(order, cartList);
            request.getSession().setAttribute("order", order);
//            request.setAttribute("cart", "");
            return "/orderSuccess.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "提交订单失败");
            return "/message.jsp";
        }
    }
}
