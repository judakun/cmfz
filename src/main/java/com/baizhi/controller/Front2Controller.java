package com.baizhi.controller;

import com.baizhi.service.FinallyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Finallycontroller
 * @Description 类
 * @Author JuDK
 * @Date 2019/12/26 10:46
 */
@RestController
@RequestMapping("detail")
public class Front2Controller {
    @Autowired
    private FinallyService finallyService;

    @RequestMapping("wen")
    public Map<String, Object> first_page(String id, String uid, HttpSession session) {
        //id 专辑id   uid 用户id
        Map<String, Object> map = new HashMap<>();
        if (id == null || uid == null) {
            map.put("error", "id或者uid不许为空");
        } else {
            Map<String, Object> stringObjectMap = finallyService.selectAlbumByID(id, session);
            map.put("introduction", stringObjectMap);
            List<Map<String, Object>> chapterlist = finallyService.selectChapterByAlbumId(id, session);
            map.put("list", chapterlist);
        }
        return map;
    }

}
