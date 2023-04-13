package com.lxl.xiaomi.controller;

import com.lxl.xiaomi.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.controller
 * Description : BaseServlet
 * Author : LiuXinLei
 * createDate : 2023/4/13 11:12
 * Version : 1.0
 */
@WebServlet(name = "BaseServlet", urlPatterns = "/baseServlet")
public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String methodName = req.getParameter("method");
        //使用反射技术执行

        String url = null;
        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            url = (String) method.invoke(this, req, resp);
            if (!StringUtils.isEmpty(url)) {
                if (url.startsWith("redirect")) {
                    url = url.substring(url.indexOf(":") + 1);
                    resp.sendRedirect(req.getContextPath() + url);
                } else if (url.startsWith("forword")) {
                    url = url.substring(url.indexOf(":") + 1);
                    req.getRequestDispatcher(url);
                }
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }


    }
}
