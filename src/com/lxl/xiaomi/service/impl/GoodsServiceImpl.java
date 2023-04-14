package com.lxl.xiaomi.service.impl;

import com.lxl.xiaomi.dao.GoodsDao;
import com.lxl.xiaomi.dao.impl.GoodsDaoImpl;
import com.lxl.xiaomi.entity.Goods;
import com.lxl.xiaomi.entity.PageBean;
import com.lxl.xiaomi.service.GoodsService;

import java.util.ArrayList;
import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.service.impl
 * Description : GoodsServiceImpl
 * Author : LiuXinLei
 * createDate : 2023/4/13 20:08
 * Version : 1.0
 */
public class GoodsServiceImpl implements GoodsService {
    GoodsDao goodsDao = new GoodsDaoImpl();

    @Override
    public List<Goods> queryByTypeId(String typeId) {
        return goodsDao.selectByTypeId();
    }


    @Override
    public PageBean<Goods> queryPageByTypeId(int pn, int ps, String where, ArrayList<Object> params) {
        long totalSize = goodsDao.selectCount(where, params);
        params.add((pn - 1) * ps);
        params.add(ps);
        List<Goods> data = goodsDao.selectPage(where, params);

        return new PageBean<>(pn, ps, totalSize, data);
    }

    @Override
    public Goods queryById(String id) {
        return goodsDao.selectById(id);
    }
}
