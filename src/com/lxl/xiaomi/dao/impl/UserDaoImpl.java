package com.lxl.xiaomi.dao.impl;

import com.lxl.xiaomi.dao.UserDao;
import com.lxl.xiaomi.entity.User;
import com.lxl.xiaomi.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.dao.impl
 * Description : UserDaoImpl
 * Author : LiuXinLei
 * createDate : 2023/4/13 9:55
 * Version : 1.0
 */
public class UserDaoImpl implements UserDao {
    @Override
    public boolean insert(User user) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "insert into tb_user VALUES (0, ?, ?, ?, ?, ?, ?, ?);";
        Object[] params = {user.getUsername(), user.getPassword(), user.getEmail(), user.getGender(), user.getFlag(), user.getRole(), user.getCode()};
        int update = 0;
        try {
            update = queryRunner.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (update != 0) {
            return true;
        }
        return false;
    }

    @Override
    public User selectOneByName(String username) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_user where username=?;";
        try {
            return queryRunner.query(sql, new BeanHandler<>(User.class), username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User selectOneByNameAndPwd(String username, String password) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_user where username=? and password=?;";
        try {
            return queryRunner.query(sql, new BeanHandler<>(User.class), username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
