package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @ClassName Role
 * @Description 类
 * @Author JuDK
 * @Date 2020/1/6 16:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Role {
    private String id;
    private String name;
    private List<Power> powers;
}
