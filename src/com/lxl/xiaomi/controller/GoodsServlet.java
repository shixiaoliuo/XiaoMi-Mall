package com.lxl.xiaomi.controller;

import com.lxl.xiaomi.entity.Goods;
import com.lxl.xiaomi.entity.PageBean;
import com.lxl.xiaomi.service.GoodsService;
import com.lxl.xiaomi.service.impl.GoodsServiceImpl;
import com.lxl.xiaomi.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.controller
 * Description : GoodsServlet
 * Author : LiuXinLei
 * createDate : 2023/4/13 20:00
 * Version : 1.0
 */
@WebServlet(name = "GoodsServlet", urlPatterns = "/goodsservlet")
public class GoodsServlet extends BaseServlet {
    GoodsService goodsService = new GoodsServiceImpl();


    public String getGoodsListByTypeId(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String typeId = req.getParameter("typeId");
        req.getSession().setAttribute("typeId", typeId);
//        resp.setContentType("application/json;charset=utf-8");
//        List<Goods> goods = goodsService.queryByTypeId(typeId);
//        resp.getWriter().write(JSON.toJSONString(goods));

        String pageNum = req.getParameter("pageNum");
        String pageSize = req.getParameter("pageSize");

        int pn = 1;//默认值
        int ps = 10;//默认值
        try {
            if (!StringUtils.isEmpty(pageNum)) {
                pn = Integer.parseInt(pageNum);
                if (pn <= 0) {
                    pn = 1;
                }
            }
            if (!StringUtils.isEmpty(pageSize)) {
                ps = Integer.parseInt(pageSize);
                if (ps <= 0) {
                    ps = 10;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        PageBean<Goods> pageBean = goodsService.queryPageByTypeId(pn, ps, typeId);
        pageBean.setStartPage(pn);


//        resp.getWriter().write(JSON.toJSONString(pageBean));
//        PrintWriter pw = resp.getWriter();
        HttpSession session = req.getSession();
        session.setAttribute("pageBean", pageBean);

        return "redirect:/goodsList.jsp";
    }
}
