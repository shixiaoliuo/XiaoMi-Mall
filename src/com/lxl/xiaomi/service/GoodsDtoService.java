package com.lxl.xiaomi.service;

import com.lxl.xiaomi.entity.GoodsDto;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.service
 * Description : GoodsDtoService
 * Author : LiuXinLei
 * createDate : 2023/4/14 9:42
 * Version : 1.0
 */
public interface GoodsDtoService {
    GoodsDto queryById(String id);
}
