package com.lxl.xiaomi.utils;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.utils
 * Description : DruidUtils
 * Author : LiuXinLei
 * createDate : 2023/4/13 9:17
 * Version : 1.0
 */
public class DruidUtils {
    private static DruidDataSource dataSource;

    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();


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

    public static Connection getConnection() {
        try {
            Connection conn = threadLocal.get();
            if (conn == null) {
                conn = dataSource.getConnection();
                threadLocal.set(conn);
            }
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeAll(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //事务有关的方法
    public static void begin() throws SQLException {
        Connection conn = getConnection();
        if (conn != null) {
            conn.setAutoCommit(false);
        }
    }

    public static void commit() throws SQLException {
        Connection conn = getConnection();
        if (conn != null) {
            conn.commit();
        }
    }

    public static void rollback() throws SQLException {
        Connection conn = getConnection();
        if (conn != null) {
            conn.rollback();
        }
    }

    public static void close() throws SQLException {
        Connection conn = getConnection();
        if (conn != null) {
            conn.close();
            threadLocal.remove();
        }
    }
}
