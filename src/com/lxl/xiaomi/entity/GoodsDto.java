package com.lxl.xiaomi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
public class GoodsDto extends Goods {
    private GoodsType goodsType;
}
