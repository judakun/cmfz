package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName AlbumService
 * @Description 接口
 * @Author JuDK
 * @Date 2019/12/19 15:05
 */
public interface AlbumService {
    //根据id查询专辑
    Album selectAlbumById(Album album);

    //分页查看专辑
    List<Album> getAlbumByPae(Integer page, Integer rows);

    //查询专辑总数
    Integer getAlbumCount();

    //获取专辑总页数
    Integer getAlbumPages(Integer rows);

    //修改专辑
    void updateAlbum(Album album);

    //删除专辑
    void deleteAlbum(Album album, HttpSession session);

    //添加专辑
    void addAlbum(Album album);

    //----------------------------------------
    //根据id查询章节
    Chapter selectChapterById(Chapter chapter);

    //分页查看章节
    List<Chapter> getChapterByPae(Integer page, Integer rows, String albumId);

    //查询专辑总数
    Integer getChapterCount(String albumId);

    //获取专辑总页数
    Integer getChapterPages(Integer rows, String albumId);

    //修改专辑
    void updateChapter(Chapter chapter);

    //删除专辑
    void deleteChapter(Chapter chapter);

    //添加专辑
    void addChapter(Chapter chapter);


}
