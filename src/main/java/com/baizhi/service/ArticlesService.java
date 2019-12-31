package com.baizhi.service;

import com.baizhi.entity.Articles;

import java.util.Map;

/**
 * @ClassName ArticlesService
 * @Description 接口
 * @Author JuDK
 * @Date 2019/12/20 14:20
 */
public interface ArticlesService {
    //展示文章
    Map<String, Object> showArticles(Integer page, Integer rows);

    //修改文章
    Map<String, Object> editArticles(String oper, Articles articles);
}
