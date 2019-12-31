package com.baizhi.service;

import com.baizhi.dao.*;
import com.baizhi.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName FinallyServiceImpl
 * @Description 类
 * @Author JuDK
 * @Date 2019/12/26 11:55
 */
@Service
public class FinallyServiceImpl implements FinallyService {
    @Autowired
    private LbtDao lbtDao;
    @Autowired
    private ArticlesDao articlesDao;
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private ChapterDao chapterDao;
    @Autowired
    private UserDao userDao;

    @Override
    public List<Map<String, Object>> getAllLbt(HttpSession session) {
        Map<String, Object> map;
        List<Map<String, Object>> list = new ArrayList<>();
        List<Lbt> lbts = lbtDao.selectAll();
        //获取保存文件的绝对路径
        String realPath = session.getServletContext().getRealPath("/image");
        for (Lbt lbt : lbts) {
            map = new HashMap<>();
            map.put("thumbnail", realPath + "\\" + lbt.getCover());
            map.put("desc", lbt.getDescribes());
            map.put("id", lbt.getId());
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getAllAlbum(HttpSession session) {
        //wen 统计对应专辑下的集数
        Map<String, Object> map;
        List<Map<String, Object>> list = new ArrayList<>();
        List<Album> albums = albumDao.selectAll();
        //获取保存文件的绝对路径
        String realPath = session.getServletContext().getRealPath("/image");
        for (Album album : albums) {
            map = new HashMap<>();
            map.put("thumbnail", realPath + "\\" + album.getCover());
            map.put("title", album.getTitle());
            map.put("author", album.getAuthor());
            map.put("type", "0");
            //获取集数
            Integer countByAlbumId = chapterDao.getCountByAlbumId(album.getId());
            map.put("set_count", countByAlbumId + "");
            //日期格式应该不对
            Date create_date = album.getCreate_date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            String format = sdf.format(create_date);
            map.put("create_date", format);
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getAllSsyj(HttpSession session) {
        Map<String, Object> map;
        List<Map<String, Object>> list = new ArrayList<>();
        List<Album> albums = albumDao.selectAll();
        //获取保存文件的绝对路径
        String realPath = session.getServletContext().getRealPath("/image");
        Example example = new Example(Articles.class);
        Example.Criteria criteria = example.createCriteria();
        //上师id不为null
        criteria.andIsNotNull("guru_id");
        List<Articles> articles = articlesDao.selectByExample(example);
        for (Articles article : articles) {
            map = new HashMap<>();
            map.put("thumbnail", "");
            map.put("title", article.getTitle());
            map.put("author", article.getAuthor());
            map.put("type", "1");
            map.put("set_count", "");
            //日期格式应该不对
            Date create_date = article.getCreate_date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            String format = sdf.format(create_date);
            map.put("create_date", format);
            list.add(map);
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getAllXmfy(HttpSession session) {
        Map<String, Object> map;
        List<Map<String, Object>> list = new ArrayList<>();
        //List<Album> albums = albumDao.selectAll();
        Example example = new Example(Articles.class);
        Example.Criteria criteria = example.createCriteria();
        //上师id不为null
        criteria.andIsNull("guru_id");
        List<Articles> articles = articlesDao.selectByExample(example);
        for (Articles article : articles) {
            map = new HashMap<>();
            map.put("thumbnail", "");
            map.put("title", article.getTitle());
            map.put("author", article.getAuthor());
            map.put("type", "1");
            map.put("set_count", "");
            //日期格式应该不对
            Date create_date = article.getCreate_date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            String format = sdf.format(create_date);
            map.put("create_date", format);
            list.add(map);
        }
        return list;
    }

    @Override
    public Map<String, Object> selectAlbumByID(String id, HttpSession session) {
        Album album = new Album();
        album.setId(id);
        Album album1 = albumDao.selectOne(album);
        Map<String, Object> map = new HashMap<>();
        //获取保存文件的绝对路径
        String realPath = session.getServletContext().getRealPath("/image");
        map.put("thumbnail", realPath + "\\" + album1.getCover());
        map.put("title", album1.getTitle());
        map.put("score", album1.getScore());
        map.put("author", album1.getAuthor());
        map.put("broadcast", album1.getBeam());
        map.put("set_count", album1.getCount());
        map.put("brief", album1.getIntro());
        //日期格式应该不对
        Date create_date = album1.getCreate_date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String format = sdf.format(create_date);
        map.put("create_date", format);
        return map;
    }

    @Override
    public List<Map<String, Object>> selectChapterByAlbumId(String id, HttpSession session) {
        List<Map<String, Object>> list = new ArrayList<>();
        Example example = new Example(Chapter.class);
        Example.Criteria criteria = example.createCriteria();
        //根据专辑id查询
        criteria.andEqualTo("album_id", id);
        List<Chapter> chapters = chapterDao.selectByExample(example);
        //获取保存文件的绝对路径
        String realPath = session.getServletContext().getRealPath("/music");
        Map<String, Object> map;
        for (Chapter chapter : chapters) {
            map = new HashMap<>();
            map.put("title", chapter.getTitle());
            map.put("download_url", realPath + "\\" + chapter.getCover());
            map.put("size", chapter.getSize());
            map.put("duration", chapter.getDuration());
            list.add(map);
        }
        return list;
    }

    @Override
    public Map<String, Object> login(String phone, String password, String code, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        //先登录
        User user = new User();
        user.setUsername(phone);
        User user1 = userDao.selectOne(user);
        if (user1 == null) {
            map.put("error", "-200");
            map.put("errmsg", "账户不存在");
        } else if (!user1.getPassword().equals(password)) {
            map.put("error", "-200");
            map.put("errmsg", "密码错误");
        } else {
            map.put("password", user1.getPassword());
            map.put("farmington", user1.getDharma());
            map.put("uid", user1.getId());
            map.put("nickname", user1.getUsername());
            map.put("gender", user1.getSex());
            //获取保存文件的绝对路径
            String realPath = session.getServletContext().getRealPath("/image");
            map.put("photo", realPath + "\\" + user1.getPhoto());
            map.put("location", user1.getProvince() + "-" + user1.getCity());
            map.put("province", user1.getProvince());
            map.put("city", user1.getCity());
            map.put("description", user1.getSign());
            map.put("phone ", user1.getPhone());
        }
        return map;
    }

    @Override
    public Map<String, Object> regist(String phone, String password) {
        Map<String, Object> map = new HashMap<>();
        //根据手机号查询
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        //根据专辑id查询
        criteria.andEqualTo("phone", phone);
        User user = userDao.selectOneByExample(example);
        if (user != null) {
            //用户已存在
            map.put("error", "-200");
            map.put("error_msg", "该手机号已经存在");
        } else {
            String id = UUID.randomUUID().toString();
            User user1 = new User();
            user1.setId(id);
            user1.setUsername(phone);
            user1.setPhone(phone);
            userDao.insert(user1);
            map.put("password", password);
            map.put("uid", id);
            map.put("phone", phone);
        }
        return map;
    }

    @Override
    public Map<String, Object> modify(String phone, String uid, String gender, MultipartFile photo, String location, String description, String nickname, String province, String city, String password, HttpSession session) {
        System.out.println("------------1-------");
        Map<String, Object> map = new HashMap<>();
        if (uid == null) {
            System.out.println("------------2.1-------");
            map.put("error", "-200");
            map.put("error_msg", "uid不能为空");
            System.out.println("------------2.2-------");
        } else {
            System.out.println("------------3.1-------");
            //准备修改数据
            if (phone != null) {
                System.out.println("------------3.2-------");
                //根据手机号查询
                Example example = new Example(User.class);
                Example.Criteria criteria = example.createCriteria();
                //根据专辑id查询
                criteria.andEqualTo("phone", phone);
                User user = userDao.selectOneByExample(example);
                if (user != null) {
                    //用户已存在
                    map.put("error", "-200");
                    map.put("error_msg", "该手机号已经存在");
                    System.out.println("------------4-------");
                } else {
                    System.out.println("------------5-------");
                    User user1 = new User();
                    user1.setPhone(phone).setId(uid);
                    if (gender != null) user1.setSex(gender);
                    if (province != null) user1.setProvince(province);
                    if (city != null) user1.setCity(city);
                    if (description != null) user1.setSign(description);
                    if (nickname != null) user1.setUsername(nickname);
                    if (password != null) user1.setPassword(password);
                    //判断是否从上传了新的文件
                    //获取保存文件的绝对路径
                    String realPath = session.getServletContext().getRealPath("/image");
                    System.out.println("------------6-------");
                    //StringUtils.isNotBlank(photo.getOriginalFilename())
                    if (photo != null) {
                        System.out.println("------------7-------");
                        //获取到上传的文件名
                        String filename = UUID.randomUUID().toString() + photo.getOriginalFilename();
                        System.out.println("查看接收到的文件名称====" + filename);
                        //创建文件对象
                        File file = new File(realPath + "/" + filename);
                        //保存到服务器的文件夹中
                        try {
                            photo.transferTo(new File(realPath + "/" + filename));
                            //删除服务器中的源文件
                            System.out.println("------------8-------");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        user1.setPhone(filename);
                    }
                    System.out.println("要修改的数据：" + user1);
                    //开始修改
                    int i = userDao.updateByPrimaryKeySelective(user1);
                    if (i != 0) {
                        System.out.println("------------9-------");
                        //修改成功，再次查询
                        User user2 = new User();
                        user2.setId(user1.getId());
                        User user3 = userDao.selectOne(user2);
                        map.put("password", user3.getPassword());
                        map.put("farmington", user3.getFname());
                        map.put("uid", user3.getId());
                        map.put("nickname", user3.getUsername());
                        map.put("gender", user3.getSex());
                        if (user3.getPhoto() != null) {
                            map.put("photo", realPath + "\\" + user3.getPhoto());
                        } else {
                            map.put("photo", "");
                        }
                        map.put("location", user3.getProvince() + "-" + user3.getCity());
                        map.put("province", user3.getProvince());
                        map.put("city", user3.getCity());
                        map.put("description", user3.getSign());
                        map.put("phone ", user3.getPhone());
                    }
                }

            }

        }
        return map;
    }

    @Override
    public List<Map<String, Object>> getAllUser(String uid) {
        List<User> users = userDao.selectAll();

        return null;
    }


}
