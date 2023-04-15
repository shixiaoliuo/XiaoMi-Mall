package com.lxl.xiaomi.controller;

import cn.dsna.util.images.ValidateCode;
import com.lxl.xiaomi.entity.Address;
import com.lxl.xiaomi.entity.User;
import com.lxl.xiaomi.service.AddressService;
import com.lxl.xiaomi.service.UserService;
import com.lxl.xiaomi.service.impl.AddressServiceImpl;
import com.lxl.xiaomi.service.impl.UserServiceImpl;
import com.lxl.xiaomi.utils.Base64Utils;
import com.lxl.xiaomi.utils.EmailUtils;
import com.lxl.xiaomi.utils.MD5Utils;
import com.lxl.xiaomi.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.server.UID;
import java.util.List;

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

    AddressService addressService = new AddressServiceImpl();

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
            EmailUtils.sendEmail(user);
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
        String auto = req.getParameter("auto");
        if (StringUtils.isEmpty(username, password)) {
            req.getSession().setAttribute("msg", "登陆失败");
            return "redirect:/login.jsp";
        }
        User user = userService.login(username, password);
        if (user != null) {
            req.getSession().setAttribute("user", user);
            if (auto != null) {
                String userInfo = username + "#" + password;
//                for (int i = 0; i < 20; i++) {
                userInfo = Base64Utils.encode(userInfo);
//                }
                Cookie cookie = new Cookie("userInfo", userInfo);
                cookie.setPath(req.getContextPath());
                cookie.setMaxAge(60 * 60 * 24 * 14);
                cookie.setHttpOnly(true);
                resp.addCookie(cookie);
            }
            return "redirect:/index.jsp";
        } else {
            req.getSession().setAttribute("msg", "登陆失败");
            return "redirect:/login.jsp";
        }
    }

    /**
     * 登出
     *
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
    public String logOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        session.invalidate();

        Cookie cookie = new Cookie("userInfo", "");
        cookie.setPath(req.getContextPath());
        cookie.setMaxAge(0);
        resp.addCookie(cookie);
        return "redirect:/login.jsp";
    }

    /**
     * 地址管理
     *
     * @param req
     * @param resp
     * @return
     * @throws IOException
     */
    public String getAddress(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        Integer id = user.getId();
        List<Address> addresses = addressService.queryById(id);
        req.getSession().setAttribute("addList", addresses);
        return "redirect:/self_info.jsp";
    }

    public String addAddress(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //name phone detail
        req.setCharacterEncoding("utf-8");
        User user = (User) req.getSession().getAttribute("user");
        Integer id = user.getId();
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String detail = req.getParameter("detail");
        Address address = new Address(0, detail, name, phone, id, 0);
        if (!StringUtils.isEmpty(name, phone, detail)) {
            addressService.add(address);
        }
        getAddress(req, resp);
        return "redirect:/self_info.jsp";
    }

    //    defaultAddress&id=7
    public String defaultAddress(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        Integer uid = user.getId();
        String id = req.getParameter("id");
        addressService.setDefault(Integer.valueOf(id), uid);
        getAddress(req, resp);
        return "redirect:/self_info.jsp";
    }

    public String updateAddress(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = (User) req.getSession().getAttribute("user");
        Integer uid = user.getId();
        Integer id = Integer.valueOf(req.getParameter("id"));
        Integer level = Integer.valueOf(req.getParameter("level"));
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String detail = req.getParameter("detail");
        Address address = new Address(id, detail, name, phone, uid, level);
        addressService.update(address);
        getAddress(req, resp);
        return "redirect:/self_info.jsp";
    }

    //deleteAddress&id=7
    public String deleteAddress(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer id = Integer.valueOf(req.getParameter("id"));
        addressService.removeById(id);
        getAddress(req, resp);
        return "redirect:/self_info.jsp";
    }

}
