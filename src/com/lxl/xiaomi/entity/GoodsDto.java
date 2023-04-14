package com.lxl.xiaomi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.entity
 * Description : GoodsDto
 * Author : LiuXinLei
 * createDate : 2023/4/14 9:37
 * Version : 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GoodsDto{
    private Integer id;
    private String name;
    private Date pubdate;
    private String picture;
    private BigDecimal price;
    private Integer star;
    private String intro;
    private Integer typeid;
    private GoodsType goodsType;
}
