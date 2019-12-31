package com.baizhi.service;

import com.baizhi.dao.LbtDao;
import com.baizhi.entity.Lbt;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * @ClassName LbtServiceImpl
 * @Description 类
 * @Author JuDK
 * @Date 2019/12/18 13:15
 */
@Service
@Transactional
public class LbtServiceImpl implements LbtService {
    @Autowired
    private LbtDao lbtDao;

    @Override
    public Integer getLbtCount() {
        List<Lbt> lbts = lbtDao.selectAll();
        return lbts.size();
    }

    @Override
    public Integer getLbtPages(Integer rows) {
        int size = lbtDao.selectAll().size();
        int pageTotal = size % rows == 0 ? size / rows : size / rows + 1;
        return pageTotal;
    }

    @Override
    public List<Lbt> getLbtByPage(Integer page, Integer rows) {
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        List<Lbt> lbts = lbtDao.selectByRowBounds(new Lbt(), rowBounds);
        //lbts.forEach(l -> System.out.println(l));
        return lbts;
    }

    @Override
    public void updateLbt(Lbt lbt) {
        boolean b = lbtDao.updateLbt(lbt);
        System.out.println(" updateLbt  ==> " + b);
    }

    @Override
    public void deleteLbt(Lbt lbt, HttpSession session) {
        lbtDao.delete(lbt);
        //还要删除服务器中对应的文件
        //获取文件夹路径
        String realPath = session.getServletContext().getRealPath("/image");
        File file = new File(realPath + "/" + lbt.getCover());
        //删除服务器中的对应文件
        boolean b = file.delete();
        System.out.println("是否成功删除===" + b);
    }

    @Override
    public void addLbt(Lbt lbt) {
        //默认状态
        lbt.setStatus("1");
        //获取系统时间
        lbt.setCreate_time(new Date());
        lbtDao.insert(lbt);
    }
}
