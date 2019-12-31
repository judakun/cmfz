package com.baizhi.dao;

import com.baizhi.entity.Lbt;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName LbtDao
 * @Description 接口
 * @Author JuDK
 * @Date 2019/12/18 13:03
 */
@Repository
public interface LbtDao extends Mapper<Lbt> {
    boolean updateLbt(Lbt lbt);
}
