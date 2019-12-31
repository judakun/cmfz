package com.baizhi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FinallyService
 * @Description 接口
 * @Author JuDK
 * @Date 2019/12/26 11:51
 */
@Service
public interface FinallyService {
    //查所有轮播图 cover desc id
    List<Map<String, Object>> getAllLbt(HttpSession session);

    //专辑集合
    List<Map<String, Object>> getAllAlbum(HttpSession session);

    //上师言教集合
    List<Map<String, Object>> getAllSsyj(HttpSession session);

    //显密法要集合
    List<Map<String, Object>> getAllXmfy(HttpSession session);

    //显示专辑及章节的详情
    Map<String, Object> selectAlbumByID(String id, HttpSession session);

    //显示专辑对应的所有章节
    List<Map<String, Object>> selectChapterByAlbumId(String id, HttpSession session);

    //登陆成功后获取用户的信息
    Map<String, Object> login(String phone, String password, String code, HttpSession session);

    //用户注册
    Map<String, Object> regist(String phone, String password);

    //用户信息修改
    Map<String, Object> modify(String phone, String uid, String gender, MultipartFile photo, String location, String description, String nickname, String province, String city, String password, HttpSession session);

    //统计所有会员,查看好友
    List<Map<String, Object>> getAllUser(String uid);
}
