package com.baizhi.service;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ClassName AlbumServiceImpl
 * @Description 类
 * @Author JuDK
 * @Date 2019/12/19 15:07
 */
@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private ChapterDao chapterDao;

    @Override
    public Album selectAlbumById(Album album) {
        Album album1 = albumDao.selectOne(album);
        return album1;
    }

    @Override
    public List<Album> getAlbumByPae(Integer page, Integer rows) {
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Album> list = albumDao.selectByRowBounds(new Album(), rowBounds);
        list.forEach(album -> System.out.println("----专辑数据-----" + album));
        return list;
    }

    @Override
    public Integer getAlbumCount() {
        List<Album> list = albumDao.selectAll();
        System.out.println("------专辑总数------" + list.size());
        return list.size();
    }

    @Override
    public Integer getAlbumPages(Integer rows) {
        int list = albumDao.selectAll().size();
        int pageCount = list % rows == 0 ? list / rows : list / rows + 1;
        System.out.println("---------专辑总条数-------" + pageCount);
        return pageCount;
    }

    @Override
    public void updateAlbum(Album album) {
        //修改专辑
        boolean b = albumDao.updateAlbum(album);
        System.out.println("---修改专辑是否成功---" + b);
    }

    @Override
    public void deleteAlbum(Album album, HttpSession session) {
        int delete = albumDao.delete(album);
        System.out.println("---是否成功删除专辑" + delete);
        //删除专辑,还需要处理对应的章节
       /* Chapter chapter = new Chapter();
        chapter.setAlbumId(album.getId());
        int delete1 = chapterDao.delete(chapter);
        if(delete==0 || delete1==0){
            throw new RuntimeException("专辑删除失败");
        }*/


    }

    @Override
    public void addAlbum(Album album) {
        //添加专辑
        int i = albumDao.insertSelective(album);
    }

    @Override
    public Chapter selectChapterById(Chapter chapter) {
        //根据id查询章节
        Chapter chapter1 = chapterDao.selectOne(chapter);
        return chapter1;
    }

    //--------------------------以下为章节--------------------------------------------
    @Override
    public List<Chapter> getChapterByPae(Integer page, Integer rows, String album_id) {
        //分页展示章节
        int begin = (page - 1) * rows;
        List<Chapter> list = chapterDao.getChapterByPageAndAlbumId(begin, rows, album_id);
        return list;
    }

    @Override
    public Integer getChapterCount(String album_id) {
        //获取章节总个数
        Integer count = chapterDao.getCountByAlbumId(album_id);
        return count;
    }

    @Override
    public Integer getChapterPages(Integer rows, String album_id) {
        //获取章节总页数
        Integer count = chapterDao.getCountByAlbumId(album_id);
        Integer totalPage = count % rows == 0 ? count / rows : count / rows + 1;
        return totalPage;
    }

    @Override
    public void updateChapter(Chapter chapter) {
        //修改章节
        boolean b = chapterDao.updateChapter(chapter);
        System.out.println("修改章节是否成功：" + b);

    }

    @Override
    public void deleteChapter(Chapter chapter) {
        //删除章节
        int i = chapterDao.deleteByPrimaryKey(chapter);
        System.out.println("---删除章节是否成功---" + i);
    }

    @Override
    public void addChapter(Chapter chapter) {
        //添加章节
        boolean b = chapterDao.insertChapter(chapter);
        System.out.println("添加章节成功：" + b);
    }
}
