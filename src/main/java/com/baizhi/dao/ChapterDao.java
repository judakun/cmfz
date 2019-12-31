package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ClassName ChapterDao
 * @Description 接口
 * @Author JuDK
 * @Date 2019/12/19 16:22
 */
@Repository
public interface ChapterDao extends Mapper<Chapter> {
    List<Chapter> getChapterByPageAndAlbumId(@Param("begin") Integer begin, @Param("rows") Integer rows, @Param("album_id") String fid);

    //总条数
    Integer getCountByAlbumId(String album_id);

    //插入章节
    boolean insertChapter(Chapter chapter);

    //修改章节
    boolean updateChapter(Chapter chapter);


}
