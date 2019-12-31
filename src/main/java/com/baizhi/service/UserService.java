package com.baizhi.service;

import com.baizhi.entity.Admin;
import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UserService
 * @Description 接口
 * @Author JuDK
 * @Date 2019/12/17 15:16
 */
public interface UserService {
    Admin adminLogin(Admin admin);

    User userLogin(User user);

    List<User> getAllUser(Integer page, Integer rows);

    Integer getCount();

    Integer getPageCount(Integer rows);

    Map<String, Object> editUser(String oper, User user);

    List<User> getAllUser();

    List<List<Integer>> getUserByDate();
}
