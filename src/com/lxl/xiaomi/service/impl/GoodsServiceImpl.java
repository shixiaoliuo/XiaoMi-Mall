package com.lxl.xiaomi.service.impl;

import com.lxl.xiaomi.dao.GoodsDao;
import com.lxl.xiaomi.dao.impl.GoodsDaoImpl;
import com.lxl.xiaomi.entity.Goods;
import com.lxl.xiaomi.entity.PageBean;
import com.lxl.xiaomi.service.GoodsService;

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
    public PageBean<Goods> queryPageByTypeId(int pn, int ps, String typeId) {
        long totalSize = goodsDao.selectCount(typeId);
        List<Goods> data = goodsDao.selectPage((pn - 1) * ps, ps,typeId);

        PageBean<Goods> pageBean = new PageBean<>(pn, ps, totalSize, data);
        pageBean.setStartPage(pn);
        return pageBean;
    }

    @Override
    public Goods queryById(String id) {
        return goodsDao.selectById(id);
    }
}
