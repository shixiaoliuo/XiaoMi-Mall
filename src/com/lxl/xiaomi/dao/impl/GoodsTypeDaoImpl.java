package com.lxl.xiaomi.dao.impl;

import com.lxl.xiaomi.dao.GoodsTypeDao;
import com.lxl.xiaomi.entity.GoodsType;
import com.lxl.xiaomi.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.dao.impl
 * Description : GoodsTypeDaoImpl
 * Author : LiuXinLei
 * createDate : 2023/4/13 16:30
 * Version : 1.0
 */
public class GoodsTypeDaoImpl implements GoodsTypeDao {
    @Override
    public List<GoodsType> selectAll() {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_goods_type;";
        try {
            return queryRunner.query(sql, new BeanListHandler<>(GoodsType.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
