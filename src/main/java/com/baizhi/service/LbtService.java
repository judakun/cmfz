package com.baizhi.service;

import com.baizhi.entity.Lbt;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName LbtService
 * @Description 接口
 * @Author JuDK
 * @Date 2019/12/18 13:05
 */
public interface LbtService {
    //查询轮播图总数
    Integer getLbtCount();

    //获取轮播图总页数
    Integer getLbtPages(Integer rows);

    //分页查看轮播图
    List<Lbt> getLbtByPage(Integer page, Integer rows);

    //修改轮播图
    void updateLbt(Lbt lbt);

    //删除轮播图
    void deleteLbt(Lbt lbt, HttpSession session);

    //添加轮播图
    void addLbt(Lbt lbt);
}
