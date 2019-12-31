package com.baizhi.controller;

import com.baizhi.entity.Lbt;
import com.baizhi.service.LbtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassName LbtController
 * @Description 类
 * @Author JuDK
 * @Date 2019/12/18 14:16
 */
@Controller
@RequestMapping("lbt")
public class LbtController {
    @Autowired
    private LbtService lbtService;

    @RequestMapping("getLbtByPage")
    @ResponseBody
    public Map<String, Object> getLbtByPage(Integer page, Integer rows) {
        Map<String, Object> maps = new HashMap<>();
        maps.put("rows", lbtService.getLbtByPage(page, rows));
        //添加当前页
        maps.put("page", page);
        //添加总页数
        maps.put("total", lbtService.getLbtPages(rows));
        //添加总条数
        maps.put("records", lbtService.getLbtCount());
        return maps;
    }

    @RequestMapping("editLbt")
    @ResponseBody
    public Map<String, String> editLbt(String oper, Lbt lbt, HttpSession session) {
        Map<String, String> maps = new HashMap<>();
        System.out.println("操作类型==" + oper + "查看要修改的用户===" + lbt);
        if (oper.equals("edit")) {
            System.out.println("--------------查看要修改的对象--------" + lbt);
            //修改
            lbtService.updateLbt(lbt);
        } else if (oper.equals("add")) {
            System.out.println("查看要添加的对象：" + lbt);
            //设置id
            String id = UUID.randomUUID().toString();
            lbt.setId(id);
            String[] split = lbt.getCover().split("\\\\");
            lbt.setCover(split[split.length - 1]);
            lbt.getCover();
            lbtService.addLbt(lbt);
            maps.put("status", "true");
            maps.put("message", lbt.getId());
            System.out.println("文件已保存至数据库。。。");
        } else if (oper.equals("del")) {
            //删除
            System.out.println("查看要删除的对象===" + lbt);
            lbtService.deleteLbt(lbt, session);
        }
        System.out.println(lbt + "===操作成功===");
        return maps;
    }

    @RequestMapping("upload")
    @ResponseBody
    public void upload(String id, MultipartFile cover, HttpSession session) {
        System.out.println("--------------------进入文件上传的Controller-------------------------");
        System.out.println("查看上传的图片==" + cover);
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

}
