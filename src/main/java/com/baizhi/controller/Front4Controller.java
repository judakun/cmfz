package com.baizhi.controller;

import com.baizhi.service.FinallyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Finallycontroller
 * @Description 类
 * @Author JuDK
 * @Date 2019/12/26 10:46
 */
@RestController
@RequestMapping("identify")
public class Front4Controller {
    //获取短信验证码接口
    @Autowired
    private FinallyService finallyService;

    @RequestMapping("obtain")
    public Map<String, Object> obtain(String phone, String code) {

        Map<String, Object> map = new HashMap<>();
        return map;
    }

}
