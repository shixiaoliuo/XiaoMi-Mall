package com.lxl.xiaomi.dao.impl;

import com.lxl.xiaomi.dao.CartDao;
import com.lxl.xiaomi.entity.Cart;
import com.lxl.xiaomi.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

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
            return queryRunner.query(sql, new BeanListHandler<>(Cart.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Cart> selectById(Integer id) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_cart where id=?;";
        try {
            return queryRunner.query(sql, new BeanListHandler<>(Cart.class), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(Cart cart) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "insert into tb_cart values(?,?,?,?);";
        Object[] params = {cart.getId(), cart.getPid(), cart.getNum(), cart.getMoney()};
        try {
            return queryRunner.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Cart selectOne(Integer id, Integer pid) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_cart where id=? and pid=?;";
        try {
            return queryRunner.query(sql, new BeanHandler<>(Cart.class), id, pid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public int updateIncrease(BigDecimal price, Integer id, Integer pid) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "update tb_cart set num=num+1 ,money=money+? where id=? and  pid=?";
        try {
            return queryRunner.update(sql, price, id, pid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteById(Integer id) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "delete from tb_cart where id=?";
        try {
            return queryRunner.update(sql, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateIncrease(Cart cart) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "update tb_cart set num=num+1, money=money+? where id=? and  pid=?";
        Object[] params = {cart.getMoney(), cart.getId(), cart.getPid()};
        try {
            return queryRunner.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public int updateDecrease(BigDecimal price, Integer id, Integer goodsId) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "update tb_cart set num=num-1 , money=money-? where id=? and  pid=?";
        try {
            return queryRunner.update(sql, price, id, goodsId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteGoods(Integer id, Integer goodsId) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "delete  from tb_cart where id=? and  pid=?;";
        try {
            return queryRunner.update(sql, id, goodsId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
