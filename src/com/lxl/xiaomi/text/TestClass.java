package com.lxl.xiaomi.text;

import com.lxl.xiaomi.dao.AddressDao;
import com.lxl.xiaomi.dao.impl.AddressDaoImpl;
import com.lxl.xiaomi.entity.Address;
import com.lxl.xiaomi.service.UserService;
import com.lxl.xiaomi.service.impl.UserServiceImpl;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.text
 * Description : TestClass
 * Author : LiuXinLei
 * createDate : 2023/4/13 10:39
 * Version : 1.0
 */
public class TestClass {
    @Test
    public void genderCode() {
        LocalDate today = LocalDate.now();
        String year = String.valueOf(today.getYear());
        String month = "";
        String day = String.valueOf(today.getDayOfMonth());
        if (today.getMonthValue() < 10) {
            month = "0" + today.getMonthValue();
//            System.out.println(month);
        } else {
            month = String.valueOf(today.getMonthValue());
        }
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 12);
        String str = year + month + day + uuid;
        System.out.println(str);
    }

    @Test
    public void testCount() {
        UserService userService = new UserServiceImpl();
        Integer countByName = userService.queryCountByName("jjl");
        System.out.println(countByName);
    }


//    @Test void defaultAddress(){
//        AddressDao addressDao = new AddressDaoImpl();
//        List<Address> addresses = addressDao.selectDefault(1);
//        for (Address address : addresses) {
//            Integer nid = address.getId();
//            int level = 1;
//            addressDao.updateLevel(level, id, nid);
//        }
//    }
}
