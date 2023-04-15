package com.lxl.xiaomi.dao;

import com.lxl.xiaomi.entity.Address;

import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.dao
 * Description : AddressDao
 * Author : LiuXinLei
 * createDate : 2023/4/14 22:38
 * Version : 1.0
 */
public interface AddressDao {
    List<Address> selectByUid(Integer id);

    int insert(Address address);

    int updateLevel(Integer level, Integer id, Integer uid);

    List<Address> selectDefault(Integer uid);

    int update(Address address);

    int deleteById(Integer id);
}
