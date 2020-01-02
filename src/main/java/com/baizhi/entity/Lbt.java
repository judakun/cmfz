package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Lbt
 * @Description 类
 * @Author JuDK
 * @Date 2019/12/18 12:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "clbt")
public class Lbt implements Serializable {
    @Id
    @ExcelIgnore
    private String id;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "头像")
    private String cover;
    @Excel(name = "描述")
    private String describes;
    @Excel(name = "状态")
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "创建时间", format = "yyyy年MM月dd日")
    private Date create_time;


}
