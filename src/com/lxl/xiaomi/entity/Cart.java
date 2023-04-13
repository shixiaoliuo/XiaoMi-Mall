package com.lxl.xiaomi.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.entity
 * Description : Cart
 * Author : LiuXinLei
 * createDate : 2023/4/13 9:27
 * Version : 1.0
 */
@Data
public class Cart {
    private Integer id;
    private Integer pid;
    private Integer num;
    private BigDecimal money;
}
