package com.lxl.xiaomi.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.entity
 * Description : Order
 * Author : LiuXinLei
 * createDate : 2023/4/13 9:21
 * Version : 1.0
 */
@Data
public class Order {
    private String id;
    private Integer uid;
    private BigDecimal money;
    private String status;
    private Date time;
    private Integer aid;
}
