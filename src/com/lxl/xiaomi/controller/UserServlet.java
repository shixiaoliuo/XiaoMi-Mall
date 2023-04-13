package com.lxl.xiaomi.controller;

import cn.dsna.util.images.ValidateCode;
import com.lxl.xiaomi.entity.User;
import com.lxl.xiaomi.service.UserService;
import com.lxl.xiaomi.service.impl.UserServiceImpl;
import com.lxl.xiaomi.utils.MD5Utils;
import com.lxl.xiaomi.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.controller
 * Description : UserServlet
 * Author : LiuXinLei
 * createDate : 2023/4/13 9:35
 * Version : 1.0
 */
@WebServlet(name = "UserServlet", urlPatterns = "/userservlet")
public class UserServlet extends BaseServlet {
    UserService userService = new UserServiceImpl();

    /**
     * 注册
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public String register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String repassword = req.getParameter("repassword");
        String email = req.getParameter("email");
        String gender = req.getParameter("gender");

        if (StringUtils.isEmpty(username, password, repassword, email, gender)) {
            return "redirect:/register.jsp";
        }

        if (!password.equals(repassword)) {
            return "redirect:/register.jsp";
        }

        User user = new User(0, username, MD5Utils.md5(password), email, gender, 0, 1, StringUtils.generateCode());
        if (userService.add(user)) {
            req.getSession().setAttribute("registerMsg", "注册成功");
//            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return "redirect:/login.jsp";
        } else {
            req.getSession().setAttribute("registerMsg", "注册失败");
//            resp.sendRedirect(req.getContextPath() + "/register.jsp");
            return "redirect:/register.jsp";
        }
    }

    /**
     * 检查是否存在该用户名
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void checkUserName(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        Integer count = userService.queryCountByName(username);
        System.out.println(count);
        if (count == null) {
            resp.getWriter().write("0");
        } else {
            resp.getWriter().write("1");
        }
    }

    /**
     * 显示验证码
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void code(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1 创建ValidateCode对象
        //参数1(width):宽度
        //参数2(height):高度
        //参数3(codeCount):验证码字符个数
        //参数4(lineCount):干扰线
        ValidateCode validateCode = new ValidateCode(100, 30, 4, 20);
        //2 获取验证码的字符串
        String code = validateCode.getCode();
        System.out.println(code);
        //3 把验证码放入session
        HttpSession session = req.getSession();
        session.setAttribute("code", code);
        //4 返回给浏览器
        validateCode.write(resp.getOutputStream());
    }

    /**
     * 校验验证码
     *
     * @param req
     * @param resp
     * @throws IOException
     */
    public void checkCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String code = (String) req.getSession().getAttribute("code");
        String code1 = req.getParameter("code");
        if (code.equalsIgnoreCase(code1)) {
            resp.getWriter().write("0");
        } else {
            resp.getWriter().write("1");
        }
    }

    /**
     * 登录
     *
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
    public String login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = MD5Utils.md5(req.getParameter("password"));
        if (StringUtils.isEmpty(username, password)) {
            req.getSession().setAttribute("msg", "登陆失败");
            return "redirect:/login.jsp";
        }
        User user = userService.login(username, password);
        if (user != null) {
            req.getSession().setAttribute("user", user);
            return "redirect:/index.jsp";
        } else {
            req.getSession().setAttribute("msg", "登陆失败");
            return "redirect:/login.jsp";
        }
    }

    public String logOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute("user");
        return "redirect:/login.jsp";
    }

}
