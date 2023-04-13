package com.lxl.xiaomi.utils;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.utils
 * Description : DruidUtils
 * Author : LiuXinLei
 * createDate : 2023/4/13 9:17
 * Version : 1.0
 */
public class DruidUtils{
    private static DruidDataSource dataSource;

    static {
        try {
            Properties properties = new Properties();
            properties.load(DruidUtils.class.getClassLoader().getResourceAsStream("db.properties"));
            dataSource = new DruidDataSource();
            dataSource.configFromPropety(properties);
            System.out.println("连接池初始化成功！");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}
