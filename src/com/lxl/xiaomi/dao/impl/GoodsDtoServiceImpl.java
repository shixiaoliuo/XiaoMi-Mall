package com.lxl.xiaomi.dao.impl;

import com.lxl.xiaomi.dao.GoodsDao;
import com.lxl.xiaomi.dao.GoodsTypeDao;
import com.lxl.xiaomi.entity.Goods;
import com.lxl.xiaomi.entity.GoodsDto;
import com.lxl.xiaomi.entity.GoodsType;
import com.lxl.xiaomi.service.GoodsDtoService;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.dao.impl
 * Description : GoodsDtoServiceImpl
 * Author : LiuXinLei
 * createDate : 2023/4/14 9:42
 * Version : 1.0
 */
public class GoodsDtoServiceImpl implements GoodsDtoService {
    GoodsDao goodsDao = new GoodsDaoImpl();
    GoodsTypeDao goodsTypeDao = new GoodsTypeDaoImpl();
    @Override
    public GoodsDto queryById(String id) {
        Goods goods = goodsDao.selectById(id);
        GoodsType goodsType = goodsTypeDao.selectByTypeId(goods.getTypeid());
        GoodsDto goodsDto = new GoodsDto();
        goodsDto.setId(goods.getId());
        goodsDto.setName(goods.getName());
        goodsDto.setPubdate(goods.getPubdate());
        goodsDto.setPicture(goods.getPicture());
        goodsDto.setPrice(goods.getPrice());
        goodsDto.setStar(goods.getStar());
        goodsDto.setIntro(goods.getIntro());
        goodsDto.setTypeid(goods.getTypeid());
        goodsDto.setGoodsType(goodsType);
        return goodsDto;
    }
}
