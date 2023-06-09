package com.lxl.xiaomi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description : pageBean
 * @Author : LiuXinLei
 * @createDate : 2023/4/13 19:12
 * @Version : 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    private int pageNum;
    //页大小
    private int pageSize;
    //总数据个数
    private long totalSize;
    //总页数
    private int pageCount;
    //数据
    private List<T> data;

    private int startPage;
    private int endPage;

    public PageBean(int pageNum, int pageSize, long totalSize, List<T> data) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalSize = totalSize;
        this.pageCount = (totalSize % pageSize == 0 ? (int) (totalSize / pageSize) : (int) (totalSize / pageSize) + 1);
        this.data = data;

        this.startPage = this.pageNum - 4;
        this.endPage = this.pageNum + 3;

        if (this.pageNum < 5) {
            this.startPage = 1;
            this.endPage = 8;
        }

        if (this.pageNum > this.pageCount - 3) {
            this.startPage = this.pageCount - 7;
            this.endPage = this.pageCount;
        }

        if (this.pageCount < 8) {
            this.startPage = 1;
            this.endPage = this.pageCount;
        }
    }
}
