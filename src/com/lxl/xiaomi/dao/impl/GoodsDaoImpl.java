package com.lxl.xiaomi.dao.impl;

import com.lxl.xiaomi.dao.GoodsDao;
import com.lxl.xiaomi.entity.Goods;
import com.lxl.xiaomi.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.dao.impl
 * Description : GoodsDaoImpl
 * Author : LiuXinLei
 * createDate : 2023/4/13 20:10
 * Version : 1.0
 */
public class GoodsDaoImpl implements GoodsDao {
    @Override
    public List<Goods> selectByTypeId() {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_goods where typeid=?;";
        try {
            return queryRunner.query(sql, new BeanListHandler<>(Goods.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public long selectCount(String where, ArrayList<Object> params) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select count(*) from tb_goods " + where;
        try {
            return queryRunner.query(sql, new ScalarHandler<>(), params.toArray());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Goods selectById(Integer id) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_goods where id=?;";
        try {
            return queryRunner.query(sql, new BeanHandler<>(Goods.class), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Goods> selectPage( String where, ArrayList<Object> params) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_goods " + where + " limit ?,? ";
        try {
            return queryRunner.query(sql, new BeanListHandler<>(Goods.class), params.toArray());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
