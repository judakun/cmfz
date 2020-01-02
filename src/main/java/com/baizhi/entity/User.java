package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName User
 * @Description 类
 * @Author JuDK
 * @Date 2019/12/17 16:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "cuser")
public class User implements Serializable {
    @Id
    @ExcelIgnore
    private String id;
    @Excel(name = "用户名称")
    private String username;
    @Excel(name = "用户密码")
    private String password;
    @ExcelIgnore
    private String salt;
    @Excel(name = "用户法号")
    private String dharma;
    @Excel(name = "省份")
    private String province;
    @Excel(name = "城市")
    private String city;
    @Excel(name = "签名")
    private String sign;
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "头像", type = 2, width = 20, height = 20)
    private String photo;
    @Excel(name = "状态")
    private String status;
    @Excel(name = "手机号")
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建日期", format = "yyyy-MM-dd HH:mm:ss")
    private Date create_date;
    @ExcelIgnore
    private String fname;

}
