package com.baizhi.controller;

import com.baizhi.service.FinallyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Finallycontroller
 * @Description 类
 * @Author JuDK
 * @Date 2019/12/26 10:46
 */
@RestController
@RequestMapping("account")
public class Front3Controller {
    @Autowired
    private FinallyService finallyService;


    @RequestMapping("login")
    public Map<String, Object> login(String phone, String password, String code, HttpSession session) {
        //password code 二选一
        Map<String, Object> map = finallyService.login(phone, password, code, session);
        return map;
    }

    @RequestMapping("regist")
    public Map<String, Object> regist(String phone, String password) {
        Map<String, Object> map = finallyService.regist(phone, password);
        return map;
    }

    @RequestMapping("modify")
    public Map<String, Object> modify(String phone, String uid, String gender, MultipartFile photo, String location, String description, String nickname, String province, String city, String password, HttpSession session) {
        System.out.println("-----------------------" + phone + "+" + uid + "+" + gender + "+" + photo + "+" + location + "+" + description + "+" + nickname + "+" + province + "+" + city);
        Map<String, Object> modify = finallyService.modify(phone, uid, gender, photo, location, description, nickname, province, city, password, session);
        return modify;
    }

    @RequestMapping("member")
    public Map<String, Object> member(String uid) {
        //查看好友，没写
        Map<String, Object> map = new HashMap<>();
        return map;
    }

}
