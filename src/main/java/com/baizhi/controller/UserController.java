package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Admin;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.SecurityCode;
import com.baizhi.util.SecurityImage;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Description 类
 * @Author JuDK
 * @Date 2019/12/17 14:26
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    //提供验证码
    @RequestMapping("getCode")
    public String getCode(HttpSession session, HttpServletResponse response) {
        System.out.println("是否进入controller");
        String code = SecurityCode.getSecurityCode();
        session.setAttribute("code", code);
        BufferedImage img = SecurityImage.createImage(code);
        OutputStream stream = null;
        try {
            stream = response.getOutputStream();
            ImageIO.write(img, "png", stream);
        } catch (Exception e) {
        }
        return null;
    }

    @RequestMapping("adminLogin")
    @ResponseBody
    public String adminLogin(Admin admin, @Param("enCode") String enCode, HttpSession session) {
        String code = (String) session.getAttribute("code");
        if (code.equals(enCode)) {
            Admin admin1 = userService.adminLogin(admin);
            if (admin1 == null) {
                //用户不存在
                return "nameError";
            } else if (admin1.getPassword() == null) {
                //密码错误
                return "pwdError";
            } else {
                session.setAttribute("user", admin1);
                return "success";
            }
        } else {
            return "codeError";
        }

    }

    @RequestMapping("getAllUser")
    @ResponseBody
    public Map<String, Object> getAllUser(Integer page, Integer rows) {
        Map map = new HashMap<>();
        map.put("rows", userService.getAllUser(page, rows));
        map.put("page", page);
        map.put("total", userService.getPageCount(rows));
        map.put("records", userService.getCount());
        return map;
    }

    @RequestMapping("editUser")
    @ResponseBody
    public Map<String, Object> editUser(String oper, User user) {
        System.out.println("---查看用户信息操作---" + oper + user);
        Map<String, Object> map = userService.editUser(oper, user);
        return map;
    }

    //用户信息以excel格式导出
    @RequestMapping("outPutUser")
    public void outPutUser(HttpServletResponse response, HttpServletRequest request) throws IOException {
        System.out.println("------------下载-------------------");
        List<User> allUser = userService.getAllUser();
        //补全图像路径，在excel中直接展示
        String realPath = request.getSession().getServletContext().getRealPath("/image");
        for (User u : allUser) {
            u.setPhoto(realPath + "\\" + u.getPhoto());
            System.out.println(u.getPhoto());
        }
        Workbook sheets = ExcelExportUtil.exportExcel(new ExportParams("用户信息", "用户表1"), User.class, allUser);
        //设置响应头
        String encode = URLEncoder.encode("user.xls", "UTF-8");
        //response.setHeader("content-disposition","attachment;filename="+encode);
        response.setHeader("content-disposition", "attachment;filename=" + encode);
        sheets.write(response.getOutputStream());
    }

    /*@RequestMapping("getUserThreed")
    public void getUserThreed(){
        System.out.println("-------------进入getUserThreed-----------");
        List<List<Integer>> list = userService.getUserByDate();
        JSONArray jsonlist = new JSONArray();
        jsonlist.add(list.get(0));
        jsonlist.add(list.get(1));
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io", "BC-7b2344ad5e70493ca8bad326f6a078fe");
        goEasy.publish("my_channel","Hello, GoEasy!");
    }*/

    @RequestMapping("getUserThreed")
    @ResponseBody
    public List<List<Integer>> getUserThreed() {
        List<List<Integer>> list = userService.getUserByDate();
        return list;
    }


}
