package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName AlbumDao
 * @Description 接口
 * @Author JuDK
 * @Date 2019/12/19 15:04
 */
@Repository
public interface AlbumDao extends Mapper<Album> {
    //修改专辑
    boolean updateAlbum(Album album);

}
