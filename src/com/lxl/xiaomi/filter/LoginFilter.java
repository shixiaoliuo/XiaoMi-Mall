package com.lxl.xiaomi.filter;

import com.lxl.xiaomi.entity.User;
import com.lxl.xiaomi.service.UserService;
import com.lxl.xiaomi.service.impl.UserServiceImpl;
import com.lxl.xiaomi.utils.Base64Utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.filter
 * Description : LoginFilter
 * Author : LiuXinLei
 * createDate : 2023/4/13 14:53
 * Version : 1.0
 */
@WebFilter(filterName = "LoginFiler", urlPatterns = "/index.jsp")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //1 判断当前是否已经登录
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
        User user = (User) request1.getSession().getAttribute("user");
        if(user!=null){
            chain.doFilter(request,response);
            return;
        }
        //2 读取cookie
        Cookie[] cookies = ((HttpServletRequest) request).getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("userInfo")){
                    try {
                        String userinfo = cookie.getValue();
                        //解码
                        // for (int i = 0; i < 7; i++) {
                        userinfo = new String(Base64.getUrlDecoder().decode(userinfo), "utf-8");
                        System.out.println(userinfo);
                        //  }
                        //拆分
                        String[] arr = userinfo.split("#");
                        //创建业务对象
                        UserService userService = new UserServiceImpl();
                        User u = userService.login(arr[0],arr[1]);
                        request1.getSession().setAttribute("user",u);
                    } catch (Exception e) {
                        e.printStackTrace();
                        cookie.setPath(((HttpServletRequest) request).getContextPath());
                        cookie.setMaxAge(0);
                        response1.addCookie(cookie);
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
