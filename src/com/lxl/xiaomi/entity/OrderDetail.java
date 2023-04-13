package com.lxl.xiaomi.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.entity
 * Description : OrderDetail
 * Author : LiuXinLei
 * createDate : 2023/4/13 9:31
 * Version : 1.0
 */
@Data
public class OrderDetail {
    private Integer id;
    private String oid;
    private Integer pid;
    private Integer num;
    private BigDecimal money;

}
