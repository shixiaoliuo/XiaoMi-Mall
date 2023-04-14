package com.lxl.xiaomi.service;

import com.lxl.xiaomi.entity.Goods;
import com.lxl.xiaomi.entity.PageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.service
 * Description : GoodsService
 * Author : LiuXinLei
 * createDate : 2023/4/13 20:08
 * Version : 1.0
 */
public interface GoodsService {
    List<Goods> queryByTypeId(String typeId);


    PageBean<Goods> queryPageByTypeId(int pn, int ps, String where, ArrayList<Object> params);

    Goods queryById(String id);
}
