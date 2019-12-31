package com.baizhi.dao;

import com.baizhi.entity.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName UserDao
 * @Description 接口
 * @Author JuDK
 * @Date 2019/12/17 16:27
 */
@Repository
public interface UserDao extends Mapper<User> {
    //查看三周内创建的用户
    Integer getUserByDate(Integer day, String sex);
}
