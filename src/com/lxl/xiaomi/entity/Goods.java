package com.lxl.xiaomi.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.entity
 * Description : Goods
 * Author : LiuXinLei
 * createDate : 2023/4/13 9:24
 * Version : 1.0
 */
@Data
public class Goods {
    private Integer id;
    private String name;
    private Date pubdate;
    private String picture;
    private BigDecimal price;
    private Integer star;
    private String intro;
    private Integer typeid;
}
