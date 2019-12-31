package com.baizhi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName EchartsController
 * @Discription
 * @Author
 * @Date 2019/12/25 0025 10:22
 * @Version 1.0
 */
@RestController
@RequestMapping("echarts")
public class EchartsController {
    @RequestMapping("getAll")
    public Map<String, Integer> getAll() {
        System.out.println("------获取echarts数据----");
        Map<String, Integer> map = new HashMap<>();
        map.put("物品1", 111);
        map.put("物品2", 222);
        map.put("物品3", 333);
        map.put("物品4", 444);
        map.put("物品5", 555);
        map.put("物品6", 666);
        /*List<Integer> list=new ArrayList<>();
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));
        list.add(new Random().nextInt(100));*/
        return map;
    }
}
