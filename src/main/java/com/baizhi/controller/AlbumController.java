package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName AlbumController
 * @Description 类
 * @Author JuDK
 * @Date 2019/12/19 14:47
 */
@Controller
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("getAlbumByPage")
    @ResponseBody
    public Map<String, Object> getAlbumByPage(Integer page, Integer rows) {
        System.out.println("---------进入album------getAlbumByPage-------");
        Map map = new HashMap<>();
        map.put("rows", albumService.getAlbumByPae(page, rows));
        map.put("page", page);
        map.put("total", albumService.getAlbumPages(rows));
        map.put("records", albumService.getAlbumCount());
        System.out.println("-----album----controller结束-------");
        return map;
    }

    //修改专辑
    @RequestMapping("editAlbum")
    @ResponseBody
    public Map<String, Object> editAlbum(String oper, Album album, HttpSession session) {
        Map maps = new HashMap<>();
        if (oper.equals("edit")) {
            System.out.println("--------------查看要修改的对象--------" + album);
            //修改
            albumService.updateAlbum(album);
        } else if (oper.equals("add")) {
            System.out.println("查看要添加的对象：" + album);
            //设置id
            String id = UUID.randomUUID().toString();
            album.setId(id);
            String[] split = album.getCover().split("\\\\");
            //设置文件名称
            album.setCover(split[split.length - 1]);
            //设置分数
            album.setScore(0.0);
            //设置数量
            album.setCount(0);
            //设置添加时间
            album.setCreate_date(new Date());
            System.out.println("---查看要添加的专题全数据---" + album);
            albumService.addAlbum(album);
            maps.put("status", "true");
            maps.put("message", album.getId());
            System.out.println("文件已保存至数据库。。。");
        } else if (oper.equals("del")) {
            //删除
            System.out.println("查看要删除的对象===" + album);
            albumService.deleteAlbum(album, session);
        }
        return maps;
    }

    //上传专辑封面
    @RequestMapping("uploadAlbum")
    @ResponseBody
    public void uploadAlbum(String id, MultipartFile cover, HttpSession session) {
        //获取到上传的文件名
        String filename = cover.getOriginalFilename();
        System.out.println("查看接收到的文件名称====" + filename);
        //获取上传文件类型
        String contentType = cover.getContentType();
        //获取保存文件的绝对路径
        String realPath = session.getServletContext().getRealPath("/image");

        File file = new File(realPath + "/" + filename);
        if (file.exists()) {
            //图片已存在
            System.out.println("照片已存在，controller不处理");
        } else {
            //图片不存在
            System.out.println("照片正常上传");
            //保存到服务器的文件夹中
            try {
                cover.transferTo(new File(realPath + "/" + filename));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    //查看专辑下的章节
    @RequestMapping("selectAllChapter")
    @ResponseBody
    public Map<String, Object> selectAllChapter(Integer page, Integer rows, String albumId) {
        Map map = new HashMap<>();
        map.put("rows", albumService.getChapterByPae(page, rows, albumId));
        map.put("page", page);
        map.put("total", albumService.getChapterPages(rows, albumId));
        map.put("records", albumService.getChapterCount(albumId));
        return map;
    }

    //编辑章节
    @RequestMapping("editChapter")
    @ResponseBody
    public Map<String, Object> editChapter(String oper, Chapter chapter, String albumId, HttpSession session) {
        Map maps = new HashMap<>();
        System.out.println("操作类型==" + oper + "查看要修改的用户===" + chapter + "-----查看专辑id---" + albumId);
        if (oper.equals("edit")) {
            System.out.println("--------------查看要修改的对象--------" + chapter);
            //修改
            //lbtService.updateLbt(lbt);
            albumService.updateChapter(chapter);
        } else if (oper.equals("add")) {
            System.out.println("查看要添加的对象：" + chapter);
            //设置id
            String id = UUID.randomUUID().toString();
            chapter.setId(id);
            String[] split = chapter.getCover().split("\\\\");
            //设置文件名称
            chapter.setCover(split[split.length - 1]);
            //设置文件上传时间
            chapter.setCreate_date(new Date());
            //设置专辑id
            chapter.setAlbum_id(albumId);
            //保存至数据库
            System.out.println("---添加数据---" + chapter);
            albumService.addChapter(chapter);
            //对应专辑的数量+1
            Album album = new Album();
            album.setId(albumId);
            Album album1 = albumService.selectAlbumById(album);
            System.out.println("---修改专辑的文章数时查询专辑：" + album1);
            Integer count = album1.getCount() + 1;
            album.setCount(count);
            //根据id修改数量
            System.out.println("添加章节时，专辑对应的章节数:" + album);
            albumService.updateAlbum(album);
            maps.put("status", "true");
            maps.put("message", chapter.getId());
        } else if (oper.equals("del")) {
            //删除
            System.out.println("查看要删除的对象===" + chapter);
            albumService.deleteChapter(chapter);
            //对应专辑数量-1
            Chapter chapter2 = albumService.selectChapterById(chapter);
            String albumId1 = chapter2.getAlbum_id();
            Album album = new Album();
            album.setId(albumId1);
            Album album1 = albumService.selectAlbumById(album);
            Integer count = album1.getCount() - 1;
            album.setCount(count);
            //根据id修改数量
            albumService.updateAlbum(album);
            //删除服务器文件
            Chapter chapter1 = albumService.selectChapterById(chapter);
            String realPath = session.getServletContext().getRealPath("/music");
            File file = new File(realPath + "/" + chapter1.getCover());
            boolean b = file.delete();
            System.out.println("---服务器章节是否删除成功--" + b);
            albumService.deleteChapter(chapter);
        }
        return maps;
    }

    //上传章节文件
    @RequestMapping("uploadChapter")
    @ResponseBody
    public void uploadChapter(String id, MultipartFile cover, HttpSession session) {
        System.out.println("-----文件上传的Controller------");
        System.out.println("查看上传的文件==" + cover);
        Chapter chapter = new Chapter();
        //获取到上传的文件名
        String filename = cover.getOriginalFilename();
        System.out.println("查看接收到的文件名称====" + filename);
        //获取保存文件的绝对路径
        String realPath = session.getServletContext().getRealPath("/music");
        //创建文件对象
        File file = new File(realPath + "/" + filename);
        //保存文件
        if (file.exists()) {
            //图片已存在
            System.out.println("文件已存在，controller不处理");
        } else {
            //图片不存在
            System.out.println("文件正常上传");
            //保存到服务器的文件夹中
            try {
                cover.transferTo(new File(realPath + "/" + filename));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //设置文件时长，修改数据库
        Encoder encoder = new Encoder();
        long ls = 0;
        MultimediaInfo m;
        try {
            m = encoder.getInfo(file);  //这里传入的是文件对象
            ls = m.getDuration() / 1000;  //得到一个long类型的时长
        } catch (Exception e) {
            System.out.println("获取音频时长有误：" + e.getMessage());
        }
        String s1 = String.valueOf(ls / 60);
        String s2 = String.valueOf(ls % 60);
        chapter.setDuration(s1 + ":" + s2);


        double v = file.length() / 1024.0 / 1024;
        BigDecimal b = new BigDecimal(v);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println("--------查看文件大小Double---" + v);
        String s3 = String.valueOf(f1);
        //设置文件大小,修改数据库
        String size = s3 + "MB";
        chapter.setSize(size);
        chapter.setId(id);
        System.out.println("----------查看最终要修改的章节信息------" + chapter);
        albumService.updateChapter(chapter);


    }
}
