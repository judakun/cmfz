package com.baizhi;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baizhi.dao.*;
import com.baizhi.entity.*;
import com.baizhi.service.AlbumService;
import com.baizhi.service.LbtService;
import com.baizhi.service.UserService;
import io.goeasy.GoEasy;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.simple.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmfzApplicationTests {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private LbtService lbtService;
    @Autowired
    private LbtDao lbtDao;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArticlesDao articlesDao;
    @Autowired
    private UserService userService;
    @Autowired
    private AlbumDao albumDao;

    @Test
    public void test1() {
        System.out.println("测试一下啊,是个屁啊，万一报错了呢,哇哇哇哇哇哇哇哇哇哇哇");
        List<Admin> admins = adminDao.selectAll();
        admins.forEach(admin -> System.out.println(admin));
    }

    @Test
    public void test2() {
        adminDao.insert(new Admin("3", "admin3", "admin3", "管理员吧"));
    }

    @Test
    public void test3() {
        List<User> users = userDao.selectAll();
        users.forEach(user -> System.out.println(user));
    }

    @Test
    public void test4() {
        int page = 1;
        int rows = 5;
        List<Lbt> lbtByPage = lbtService.getLbtByPage(page, rows);
        lbtByPage.forEach(lbt -> System.out.println("查询结果：" + lbt));
        /*Integer lbtCount = lbtService.getLbtCount();
        System.out.println("总条数：" + lbtCount);
        Integer lbtPages = lbtService.getLbtPages(rows);
        System.out.println("总页数：" + lbtPages);*/
    }

    @Test
    public void test5() {
        Lbt lbt = new Lbt();
        lbt.setName("测试轮播图1").setCover("bg.jpg").setDescribes("测试一下是否可以添加");
        lbtService.addLbt(lbt);
    }

    @Test
    public void test6() {
        Lbt lbt = new Lbt();
        lbt.setId("3").setName("测试修改是否成功").setDescribes("再试一下修改");
        lbtService.updateLbt(lbt);

    }

    @Test
    public void test7() {
        Lbt lbt = new Lbt();
        lbt.setId("4");
        lbt.setName("你是猪吗？不让我修改");
        lbt.setDescribes("再试一次");
        //int i = lbtDao.updateByPrimaryKey(lbt);
        /*int i = lbtDao.updateByPrimaryKey(lbt);*/
        boolean b = lbtDao.updateLbt(lbt);
        System.out.println("--------查看是否修改------" + b);
    }

    @Test
    public void test8() {
        Admin admin = new Admin();
        admin.setId("4");
        admin.setUsername("admin444");
        admin.setPassword("admin4444");
        admin.setNickname("测试管理员444");
        //int i = adminDao.insert(admin);
        //int i = adminDao.delete(admin);
        int i = adminDao.updateByPrimaryKeySelective(admin);
        System.out.println(i);
    }

    @Test
    public void test9() {
        List<Album> list = albumService.getAlbumByPae(1, 2);
        list.forEach(album -> System.out.println(album));

    }

    @Test
    public void test10() {
        //查询所有文章
        List<Articles> articles = articlesDao.selectAll();
        articles.forEach(articles1 -> System.out.println(articles1));
        //添加文章
        /*Articles articles1 = new Articles("2","文章二","内容二",new Date(),"作者",null);
        int insert = articlesDao.insert(articles1);
        System.out.println(insert);*/
        //修改文章
        /*Articles articles = new Articles();
        articles.setId("2");
        articles.setTitle("修改文章标题二");
        int i = articlesDao.updateByPrimaryKeySelective(articles);
        System.out.println(i);*/
    }

    //Poi------------------jxl
    @Test
    public void test11() {
        //创建Excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建单元格列cell格式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        //设置字体
        HSSFFont font = workbook.createFont();
        //创建工作表
        HSSFSheet sheet = workbook.createSheet("工作表");
        //创建行row
        HSSFRow row = sheet.createRow(0);
        //创建列
        HSSFCell cell = row.createCell(0);
        //设置列
        cell.setCellValue("列值1");

    }

    //EasyPoi
    @Test
    public void test12() throws IOException {
        List<Lbt> lbts = lbtDao.selectAll();
        Workbook sheets = ExcelExportUtil.exportBigExcel(new ExportParams("用户信息", "用户表"), Lbt.class, lbts);
        sheets.write(new FileOutputStream("E:/lbt.xlsx"));
    }

    //EasyPoi文件的导入
    @Test
    public void test13() throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        List<User> list = ExcelImportUtil.importExcel(new FileInputStream("C:/Users/jdk/Downloads/user.xls"),
                User.class, params);
        list.forEach(user -> System.out.println(user));

    }

    @Test
    public void test14() throws Exception {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        List<User> list = ExcelImportUtil.importExcel(new FileInputStream("C:/Users/jdk/Downloads/user.xls"),
                User.class, params);
        for (User s : list) {
            System.out.println(s);
        }
    }

    @Test
    public void test15() {
        User user = new User();
        user.setId(UUID.randomUUID().toString()).setUsername("aaa").setPassword("aaa").setPhoto("b1.jpg").setPhone("123").setStatus("激活").setCity("河南省").setCity("信阳市").setDharma("asd").setCreate_date(new Date()).setSex("女");
        userDao.insert(user);
        List<List<Integer>> list = userService.getUserByDate();
        JSONArray jsonArray = new JSONArray();
        jsonArray.addAll(list);
        String s = jsonArray.toJSONString();
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-7b2344ad5e70493ca8bad326f6a078fe");
        goEasy.publish("my_channel", s);

    }

    @Test
    public void test16() {
       /* List<User> userByDate = userDao.getUserByDate(7,"男");
        userByDate.forEach(user -> System.out.println(user));*/
      /*  Integer b1 = userDao.getUserByDate(7, "男");
        System.out.println(b1);*/
       /* Map<String, List<Integer>> map = userService.getUserByDate();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("jsonMap",map);
        System.out.println(jsonObject);*/
        /*List<Album> albums = albumDao.selectAll();
        albums.forEach(album -> System.out.println(album));*/
        Example example = new Example(Articles.class);
        Example.Criteria criteria = example.createCriteria();
        //上师id不为null
        criteria.andIsNotNull("guru_id");
        List<Articles> articles = articlesDao.selectByExample(example);
        articles.forEach(articles1 -> System.out.println(articles1));
    }
}
