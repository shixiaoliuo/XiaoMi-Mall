package com.lxl.xiaomi.dao.impl;

import com.lxl.xiaomi.dao.GoodsDao;
import com.lxl.xiaomi.entity.Goods;
import com.lxl.xiaomi.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
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
    public long selectCount(String typeId) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select count(*) from tb_goods where typeid=?;";
        try {
            return queryRunner.query(sql, new ScalarHandler<>(),typeId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Goods> selectPage(int pn, int ps,String typeId) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_goods where typeid=? limit ?,?;";
        try {
            return queryRunner.query(sql, new BeanListHandler<>(Goods.class),typeId, pn, ps);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
