package com.lxl.xiaomi.service;

import com.lxl.xiaomi.entity.Address;

import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.service
 * Description : AddressService
 * Author : LiuXinLei
 * createDate : 2023/4/14 22:32
 * Version : 1.0
 */
public interface AddressService {
    List<Address> queryById(Integer id);

    boolean add(Address address);

    boolean setDefault(Integer id, Integer uid);

    boolean update(Address address);

    boolean removeById(Integer id);
}
