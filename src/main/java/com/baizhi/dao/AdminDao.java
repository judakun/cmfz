package com.baizhi.dao;

import com.baizhi.entity.Admin;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName AdminDao
 * @Description 接口
 * @Author JuDK
 * @Date 2019/12/17 13:05
 */
@Repository
public interface AdminDao extends Mapper<Admin> {
    Admin queryOne(String username);

}
