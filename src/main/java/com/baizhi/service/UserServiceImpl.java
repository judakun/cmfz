package com.baizhi.service;

import com.baizhi.annotation.AddCache;
import com.baizhi.dao.AdminDao;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description 类
 * @Author JuDK
 * @Date 2019/12/17 15:17
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Admin adminLogin(Admin admin) {
        Admin admin1 = new Admin();
        admin1.setUsername(admin.getUsername());
        Admin admin2 = adminDao.selectOne(admin1);
        if (admin2 != null) {
            //用户存在
            if (admin2.getPassword().equals(admin.getPassword())) {
                //密码正确
                return admin2;
            } else {
                //密码错误
                return new Admin();
            }
        } else {
            //用户不存在
            return null;
        }

    }

    @Override
    public User userLogin(User user) {

        return null;
    }

    @AddCache
    @Override
    public List<User> getAllUser(Integer page, Integer rows) {
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<User> users = userDao.selectByRowBounds(new User(), rowBounds);
        users.forEach(user -> System.out.println("----专辑数据-----" + user));
        return users;
    }

    @AddCache
    @Override
    public Integer getCount() {
        int i = userDao.selectCount(new User());
        return i;
    }

    @AddCache
    @Override
    public Integer getPageCount(Integer rows) {
        int i = userDao.selectCount(new User());
        Integer pageCount = i % rows == 0 ? i / rows : i / rows + 1;
        return pageCount;
    }

    @Override
    public Map<String, Object> editUser(String oper, User user) {
        Map<String, Object> map = new HashMap<>();
        if (oper.equals("edit")) {
            userDao.updateByPrimaryKeySelective(user);
        }
        map.put("status", "true");
        map.put("message", user.getId());
        return map;
    }

    @AddCache
    @Override
    public List<User> getAllUser() {
        List<User> users = userDao.selectAll();
        return users;
    }

    @Override
    public List<List<Integer>> getUserByDate() {
        //三周内每周注册的男女比例
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> bList = new ArrayList<>();
        List<Integer> gList = new ArrayList<>();
        //第一周男生
        Integer b1 = userDao.getUserByDate(7, "男");
        bList.add(b1);
        //第一周女生
        Integer g1 = userDao.getUserByDate(7, "女");
        gList.add(g1);
        //第二周男生
        Integer b2 = userDao.getUserByDate(14, "男") - b1;
        bList.add(b2);
        //第二周女生
        Integer g2 = userDao.getUserByDate(14, "女") - g1;
        gList.add(g2);
        //第三周男生
        Integer b3 = userDao.getUserByDate(21, "男") - b1 - b2;
        bList.add(b3);
        //第三周女生
        Integer g3 = userDao.getUserByDate(21, "女") - g1 - g2;
        gList.add(g3);
        list.add(bList);
        list.add(gList);
        /*map.put("boy",bList);
        map.put("girl",gList);*/
        return list;
    }

}
