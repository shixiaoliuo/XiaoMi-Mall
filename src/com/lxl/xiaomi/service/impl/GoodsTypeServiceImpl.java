package com.lxl.xiaomi.service.impl;

import com.lxl.xiaomi.controller.GoodsTypeServlet;
import com.lxl.xiaomi.dao.GoodsTypeDao;
import com.lxl.xiaomi.dao.impl.GoodsTypeDaoImpl;
import com.lxl.xiaomi.entity.GoodsType;
import com.lxl.xiaomi.service.GoodsTypeService;

import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.service.impl
 * Description : GoodsTypeServiceImpl
 * Author : LiuXinLei
 * createDate : 2023/4/13 16:26
 * Version : 1.0
 */
public class GoodsTypeServiceImpl implements GoodsTypeService {
    GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();
    @Override
    public List<GoodsType> queryList() {
        return goodsTypeDao.selectAll();
    }
}
