package com.lxl.xiaomi.dao.impl;

import com.lxl.xiaomi.dao.OrderDetailDao;
import com.lxl.xiaomi.entity.OrderDetail;
import com.lxl.xiaomi.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.dao.impl
 * Description : OrderDetailDaoImpl
 * Author : LiuXinLei
 * createDate : 2023/4/15 14:21
 * Version : 1.0
 */
public class OrderDetailDaoImpl implements OrderDetailDao {
    @Override
    public int insert(OrderDetail orderDetail) {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into tb_orderdetail values(0,?,?,?,?);";
        Object[] params = {orderDetail.getOid(), orderDetail.getPid(), orderDetail.getNum(), orderDetail.getMoney()};
        try {
            Connection connection = null;
            connection = DruidUtils.getConnection();
            return queryRunner.update(connection, sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
