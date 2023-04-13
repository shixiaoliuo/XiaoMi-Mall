package com.lxl.xiaomi.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.utils
 * Description : MD5Utils
 * Author : LiuXinLei
 * createDate : 2023/4/13 14:26
 * Version : 1.0
 */
public class MD5Utils {
    public static String md5(String password) {
        try {
            //1建消息摘要对象
            MessageDigest digest = MessageDigest.getInstance("md5");
            //2更新加密数据
            digest.update(password.getBytes("utf-8"));
            //3获取加密数据
            byte[] data = digest.digest();
            return new BigInteger(1, data).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
