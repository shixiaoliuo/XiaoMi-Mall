package com.lxl.xiaomi.service;

import com.lxl.xiaomi.entity.GoodsType;

import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.service
 * Description : GoodsTypeService
 * Author : LiuXinLei
 * createDate : 2023/4/13 16:26
 * Version : 1.0
 */
public interface GoodsTypeService {
    List<GoodsType> queryList();

    List<GoodsType> queryListByLevel(int i);

}
