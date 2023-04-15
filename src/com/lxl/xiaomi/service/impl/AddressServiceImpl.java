package com.lxl.xiaomi.service.impl;

import com.lxl.xiaomi.dao.AddressDao;
import com.lxl.xiaomi.dao.impl.AddressDaoImpl;
import com.lxl.xiaomi.entity.Address;
import com.lxl.xiaomi.service.AddressService;

import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.service.impl
 * Description : AddressServiceImpl
 * Author : LiuXinLei
 * createDate : 2023/4/14 22:32
 * Version : 1.0
 */
public class AddressServiceImpl implements AddressService {
    AddressDao addressDao = new AddressDaoImpl();

    @Override
    public List<Address> queryById(Integer id) {

        return addressDao.selectByUid(id);
    }

    @Override
    public boolean add(Address address) {
        int i = addressDao.insert(address);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setDefault(Integer id, Integer uid) {
        List<Address> addresses = addressDao.selectDefault(uid);
        for (Address address : addresses) {
            Integer nid = address.getId();
            int level = 1;
            addressDao.updateLevel(level, nid, uid);
        }
        addressDao.updateLevel(0, id, uid);
        return false;
    }

    @Override
    public boolean update(Address address) {
        int i = addressDao.update(address);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean removeById(Integer id) {
        addressDao.deleteById(id);
        return false;
    }
}
