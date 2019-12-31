package com.baizhi.service;

import com.baizhi.dao.ArticlesDao;
import com.baizhi.entity.Articles;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @ClassName ArticlesServiceImpl
 * @Description 类
 * @Author JuDK
 * @Date 2019/12/20 14:30
 */
@Service
@Transactional
public class ArticlesServiceImpl implements ArticlesService {
    @Autowired
    private ArticlesDao articlesDao;

    @Override
    public Map<String, Object> showArticles(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<>();
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Articles> list = articlesDao.selectByRowBounds(new Articles(), rowBounds);
        map.put("rows", list);
        map.put("page", page);
        int i = articlesDao.selectCount(new Articles());
        map.put("records", i);
        map.put("total", i % rows == 0 ? i / rows : i / rows + 1);
        return map;
    }

    @Override
    public Map<String, Object> editArticles(String oper, Articles articles) {
        Map<String, Object> map = new HashMap<>();
        System.out.println("oper进入编辑Service:" + oper + "-----" + articles);
        if (oper.equals("add")) {
            String id = UUID.randomUUID().toString();
            articles.setId(id);
            articles.setCreate_date(new Date());
            articlesDao.insertSelective(articles);
            map.put("status", "true");
            map.put("message", articles.getId());
        }
        if (oper.equals("edit")) {
            int i = articlesDao.updateByPrimaryKeySelective(articles);
            System.out.println("修改成功：" + i);
        }
        if (oper.equals("del")) {
            int delete = articlesDao.delete(articles);
            System.out.println("删除成功：" + delete);
        }
        return map;
    }
}
