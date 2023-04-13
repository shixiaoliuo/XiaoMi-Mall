package com.lxl.xiaomi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.entity
 * Description : User
 * Author : LiuXinLei
 * createDate : 2023/4/13 9:19
 * Version : 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String gender;
    private Integer flag;
    private Integer role;
    private String code;
}
