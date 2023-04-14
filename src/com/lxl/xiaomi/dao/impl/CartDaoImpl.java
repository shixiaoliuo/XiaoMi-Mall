package com.lxl.xiaomi.dao.impl;

import com.lxl.xiaomi.dao.CartDao;
import com.lxl.xiaomi.entity.Cart;
import com.lxl.xiaomi.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Queue;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.dao.impl
 * Description : CartDaoImpl
 * Author : LiuXinLei
 * createDate : 2023/4/14 10:23
 * Version : 1.0
 */
public class CartDaoImpl implements CartDao {
    @Override
    public List<Cart> selectAll() {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_cart;";
        try {
            return queryRunner.query(sql,new BeanListHandler<>(Cart.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
