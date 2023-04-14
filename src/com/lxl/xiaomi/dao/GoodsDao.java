package com.lxl.xiaomi.dao;

import com.lxl.xiaomi.entity.Goods;

import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.dao
 * Description : GoodsDao
 * Author : LiuXinLei
 * createDate : 2023/4/13 20:10
 * Version : 1.0
 */
public interface GoodsDao {
    List<Goods> selectByTypeId();

    List<Goods> selectPage(int pn, int ps,String typeId);

    long selectCount(String typeId);

    Goods selectById(String id);
}
