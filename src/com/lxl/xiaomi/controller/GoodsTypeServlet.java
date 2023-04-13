package com.lxl.xiaomi.controller;

import com.lxl.xiaomi.entity.GoodsType;
import com.lxl.xiaomi.service.GoodsTypeService;
import com.lxl.xiaomi.service.impl.GoodsTypeServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.controller
 * Description : GoodsTypeServlet
 * Author : LiuXinLei
 * createDate : 2023/4/13 15:58
 * Version : 1.0
 */
@WebServlet(name = "GoodsTypeServlet", urlPatterns = "/goodstypeservlet")
public class GoodsTypeServlet extends BaseServlet {

    GoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();

    public List<GoodsType> goodstypelist(HttpServletRequest req, HttpServletResponse resp) {
        return goodsTypeService.queryList();
    }

}
