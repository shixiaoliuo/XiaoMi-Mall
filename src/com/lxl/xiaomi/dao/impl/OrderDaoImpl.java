package com.lxl.xiaomi.dao.impl;

import com.lxl.xiaomi.dao.OrderDao;
import com.lxl.xiaomi.entity.Order;
import com.lxl.xiaomi.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
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

    @Override
    public int insert(Order order) {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into tb_order values(?,?,?,?,?,?);";
        Object[] params = {order.getId(), order.getUid(), order.getMoney(), order.getStatus(), order.getTime(), order.getAid()};
        try {
            Connection connection = null;
            connection = DruidUtils.getConnection();
            return queryRunner.update(connection, sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order selectById(String oid) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_order where id=?;";
        try {
            return queryRunner.query(sql, new BeanHandler<>(Order.class), oid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateStatus(Order order) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "update tb_order set status=?  where id=?;";
        try {
            return queryRunner.update(sql,order.getStatus(),order.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
