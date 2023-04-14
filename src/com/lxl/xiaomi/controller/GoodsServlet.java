package com.lxl.xiaomi.controller;

import com.lxl.xiaomi.dao.impl.GoodsDtoServiceImpl;
import com.lxl.xiaomi.entity.Goods;
import com.lxl.xiaomi.entity.GoodsDto;
import com.lxl.xiaomi.entity.PageBean;
import com.lxl.xiaomi.service.GoodsDtoService;
import com.lxl.xiaomi.service.GoodsService;
import com.lxl.xiaomi.service.impl.GoodsServiceImpl;
import com.lxl.xiaomi.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

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
    GoodsDtoService goodsDtoService = new GoodsDtoServiceImpl();


    public String getGoodsListByTypeId(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String typeId = req.getParameter("typeId");
        String name = req.getParameter("name");
        req.getSession().setAttribute("typeId", typeId);
//        resp.setContentType("application/json;charset=utf-8");
//        List<Goods> goods = goodsService.queryByTypeId(typeId);
//        resp.getWriter().write(JSON.toJSONString(goods));

        String pageNum = req.getParameter("pageNum");
        String pageSize = req.getParameter("pageSize");

        int pn = 1;//默认值
        int ps = 8;//默认值
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

        StringBuilder where = new StringBuilder();
        ArrayList<Object> params = new ArrayList<>();
        if (!StringUtils.isEmpty(typeId)) {
            try {
                int tid = Integer.parseInt(typeId);
                where.append("and typeid=?");
                params.add(tid);
            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }
        if (!StringUtils.isEmpty(name)) {
            where.append("and name like ?");
            params.add("%" + name + "%");
        }
        if (where.length() > 0) {
            where.replace(0, 3, "where");
        } else {
            return "forword:/index.jsp";
        }

        PageBean<Goods> pageBean = goodsService.queryPageByTypeId(pn, ps, where.toString(), params);


//        resp.getWriter().write(JSON.toJSONString(pageBean));
//        PrintWriter pw = resp.getWriter();
        HttpSession session = req.getSession();
        session.setAttribute("pageBean", pageBean);

        return "redirect:/goodsList.jsp";
    }

    public String getGoodsById(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");
//        Goods goods = goodsService.queryById(id);
//        req.getSession().setAttribute("goods", goods);
        GoodsDto goodsDto = goodsDtoService.queryById(Integer.valueOf(id));
        req.getSession().setAttribute("goods", goodsDto);
        return "redirect:/goodsDetail.jsp";
    }
}
