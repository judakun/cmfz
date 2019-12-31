package com.baizhi.dao;

import com.baizhi.entity.Articles;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName ArticlesDao
 * @Description 接口
 * @Author JuDK
 * @Date 2019/12/20 14:11
 */
@Repository
public interface ArticlesDao extends Mapper<Articles> {
}
