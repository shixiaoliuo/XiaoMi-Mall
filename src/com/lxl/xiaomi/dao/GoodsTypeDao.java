package com.lxl.xiaomi.dao;

import com.lxl.xiaomi.entity.GoodsType;

import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.dao
 * Description : GoodsTypeDao
 * Author : LiuXinLei
 * createDate : 2023/4/13 16:29
 * Version : 1.0
 */
public interface GoodsTypeDao {
    List<GoodsType> selectAll();

    List<GoodsType> selectByLevel(int level);

    GoodsType selectByTypeId(Integer typeid);
}
