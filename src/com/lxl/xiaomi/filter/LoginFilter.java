package com.lxl.xiaomi.filter;

import com.lxl.xiaomi.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.filter
 * Description : LoginFilter
 * Author : LiuXinLei
 * createDate : 2023/4/13 14:53
 * Version : 1.0
 */
@WebFilter(filterName = "LoginFiler", urlPatterns = "/login.jsp")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        User user = (User) req.getSession().getAttribute("user");
        if (user!=null){
            resp.sendRedirect(req.getContextPath()+"/index.jsp");
        }
        filterChain.doFilter(req,resp);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
