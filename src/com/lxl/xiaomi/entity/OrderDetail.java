package com.lxl.xiaomi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetail {
    private Integer id;
    private String oid;
    private Integer pid;
    private Integer num;
    private BigDecimal money;



}
