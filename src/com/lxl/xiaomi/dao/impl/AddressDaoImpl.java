package com.lxl.xiaomi.dao.impl;

import com.lxl.xiaomi.dao.AddressDao;
import com.lxl.xiaomi.entity.Address;
import com.lxl.xiaomi.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Project : Xiaomi Mall
 * Package : com.lxl.xiaomi.dao.impl
 * Description : AddressDaoImpl
 * Author : LiuXinLei
 * createDate : 2023/4/14 22:39
 * Version : 1.0
 */
public class AddressDaoImpl implements AddressDao {
    @Override
    public List<Address> selectByUid(Integer id) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_address where uid=?";
        try {
            return queryRunner.query(sql, new BeanListHandler<>(Address.class), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insert(Address address) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "insert into tb_address values (0,?,?,?,?,?);";
        Object[] params = {address.getDetail(), address.getName(), address.getPhone(), address.getUid(), address.getLevel()};
        try {
            return queryRunner.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Address> selectDefault(Integer uid) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "select * from tb_address where  uid=? and level=1;";
        try {
            return queryRunner.query(sql, new BeanListHandler<>(Address.class), uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(Address address) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "update tb_address set name=?,phone=?,detail=? where id=? and uid=? ;";
        Object[] params = {address.getName(), address.getPhone(), address.getDetail(), address.getId(), address.getUid()};
        try {
            return queryRunner.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteById(Integer id) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        String sql = "delete from tb_address where id=? ;";
        try {
            return queryRunner.update(sql, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateLevel(Integer level, Integer id, Integer uid) {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
        level = level == 1 ? 0 : 1;
        String sql = "update tb_address set level=? where id=? and uid=?;";
        try {
            return queryRunner.update(sql, level, id, uid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
