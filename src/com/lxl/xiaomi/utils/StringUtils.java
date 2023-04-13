package com.lxl.xiaomi.utils;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.utils
 * Description : StringUtils
 * Author : LiuXinLei
 * createDate : 2023/4/13 9:46
 * Version : 1.0
 */
public class StringUtils {
    public static boolean isEmpty(String... str) {
        for (String s : str) {
            if (s==null||s.trim().length() == 0) {
                return true;
            }
        }
        return false;
    }

    public static String generateCode() {
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
        return year + month + day + uuid;
    }
}
