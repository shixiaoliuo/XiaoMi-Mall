package com.lxl.xiaomi.dao.impl;

import com.lxl.xiaomi.dao.OrderDao;
import com.lxl.xiaomi.entity.Order;
import com.lxl.xiaomi.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.dao.impl
 * Description : OrderDaoImpl
 * Author : LiuXinLei
 * createDate : 2023/4/13 19:48
 * Version : 1.0
 */
public class OrderDaoImpl implements OrderDao {
    @Override
    public List<Order> selectAll() {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_order;";
        try {
            return queryRunner.query(sql, new BeanListHandler<>(Order.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
