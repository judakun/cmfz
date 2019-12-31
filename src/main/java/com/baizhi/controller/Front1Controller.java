package com.baizhi.controller;

import com.baizhi.service.FinallyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("cmfz")
public class Front1Controller {
    @Autowired
    private FinallyService finallyService;

    @RequestMapping("first_page")
    public Map<String, Object> first_page(String uid, String type, String sub_type, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        if (uid == null || type == null) {
            //传入参数错误
            map.put("error", "不能为空");
        } else {
            if (type.equals("all")) {
                map.put("header", finallyService.getAllLbt(session));
            }
            if (type.equals("wen")) {
                map.put("body", finallyService.getAllAlbum(session));
            }
            if (type.equals("si")) {
                if (sub_type == null) {
                    map.put("error", "sub_type不能为空");
                } else {
                    if (sub_type.equals("ssyj")) {
                        map.put("body", finallyService.getAllSsyj(session));
                    }
                    if (sub_type.equals("xmfy")) {
                        map.put("body", finallyService.getAllXmfy(session));
                    }
                }

            }
        }
        return map;
    }

}
