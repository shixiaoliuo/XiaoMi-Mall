package com.lxl.xiaomi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Cart {
    private Integer id;
    private Integer pid;
    private Integer num;
    private BigDecimal money;
}
