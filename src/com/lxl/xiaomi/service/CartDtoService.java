package com.lxl.xiaomi.service;

import com.lxl.xiaomi.entity.CartDto;

import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.service
 * Description : CartDtoService
 * Author : LiuXinLei
 * createDate : 2023/4/14 14:21
 * Version : 1.0
 */
public interface CartDtoService {
    List<CartDto> queryMap(Integer id);

    boolean removeNum(Integer id, Integer goodsId, Integer num);

    boolean remove(Integer id, Integer goodsId);

    boolean addNum(Integer id, Integer goodsId);

    boolean clearAll(Integer id);
}
