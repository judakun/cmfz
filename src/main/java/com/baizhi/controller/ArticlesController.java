package com.baizhi.controller;

import com.baizhi.entity.Articles;
import com.baizhi.service.ArticlesService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

/**
 * @ClassName ArticlesController
 * @Description 类
 * @Author JuDK
 * @Date 2019/12/20 14:32
 */
@Controller
@RequestMapping("articles")
public class ArticlesController {
    @Autowired
    private ArticlesService articlesService;

    //jqGrid展示所有文章
    @RequestMapping("showArticles")
    @ResponseBody
    public Map<String, Object> showArticles(Integer page, Integer rows) {
        Map<String, Object> map = articlesService.showArticles(page, rows);
        return map;
    }

    //编辑文章
    @RequestMapping("edit")
    @ResponseBody
    public Map<String, Object> edit(Articles article, String oper) {
        System.out.println("进行的操作：" + oper);
        Map<String, Object> map = articlesService.editArticles(oper, article);
        return map;
    }

    //kindeditor上传文件
    @RequestMapping("upload")
    @ResponseBody
    public Map<String, Object> upload(MultipartFile img, HttpServletRequest request) throws UnknownHostException {

        Map<String, Object> map = new HashMap<>();
        String name = UUID.randomUUID().toString() + img.getOriginalFilename();
        try {
            img.transferTo(new File(request.getSession().getServletContext().getRealPath("/image"), name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("error", 0);
        String scheme = request.getScheme();//http
        InetAddress localHost = InetAddress.getLocalHost();//ip   PC-201910311730/192.168.9.20
        String s = localHost.toString();
        String ip = s.split("/")[1];
        int serverPort = request.getServerPort();//8989
        String contextPath = request.getContextPath();//    /cmfz
        String url = scheme + "://" + ip + ":" + serverPort + contextPath + "/image/" + name;
        map.put("url", url);
        return map;
    }

    //kindeditor的网络文件资源库
    @RequestMapping("getAll")
    @ResponseBody
    public Map<String, Object> getAll(HttpServletRequest request) throws UnknownHostException {

        Map<String, Object> map = new HashMap<>();
        String realPath = request.getSession().getServletContext().getRealPath("/image");
        File files = new File(realPath);
        String[] names = files.list();
        List<Map<String, Object>> list = new ArrayList<>();
        for (String name : names) {
            Map<String, Object> file = new HashMap<>();
            file.put("is_dir", false);
            file.put("has_file", false);
            File file1 = new File(realPath, name);
            file.put("filesize", file1.length());
            file.put("dir_path", "");
            file.put("is_photo", true);
            file.put("filetype", FilenameUtils.getExtension(name));
            file.put("filename", name);
            file.put("datetime", "2018-06-06 00:36:39");
            list.add(file);
        }
        map.put("moveup_dir_path", "");
        map.put("current_dir_path", "");
        String scheme = request.getScheme();//http
        InetAddress localHost = InetAddress.getLocalHost();//ip   PC-201910311730/192.168.9.20
        String s = localHost.toString();
        String ip = s.split("/")[1];
        int serverPort = request.getServerPort();//8989
        String contextPath = request.getContextPath();//    /cmfz
        String url = scheme + "://" + ip + ":" + serverPort + contextPath + "/image/";
        map.put("current_url", url);
        map.put("total_count", names.length);
        map.put("file_list", list);
        return map;
    }
}
