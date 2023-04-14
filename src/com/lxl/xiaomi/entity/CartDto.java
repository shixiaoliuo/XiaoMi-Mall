package com.lxl.xiaomi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.entity
 * Description : CartDto
 * Author : LiuXinLei
 * createDate : 2023/4/14 14:12
 * Version : 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartDto {
    private Integer id;
    private Integer pid;
    private Integer num;
    private BigDecimal money;
    private Goods goods;
}
