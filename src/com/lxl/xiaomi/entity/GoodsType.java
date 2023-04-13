package com.lxl.xiaomi.entity;

import lombok.Data;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.entity
 * Description : GoodsType
 * Author : LiuXinLei
 * createDate : 2023/4/13 9:29
 * Version : 1.0
 */
@Data
public class GoodsType {
    private Integer id;
    private String name;
    private Integer level;
    private Integer parent;
}
