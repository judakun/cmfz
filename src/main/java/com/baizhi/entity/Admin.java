package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import javax.persistence.Table;

/**
 * @ClassName Admin
 * @Description 类
 * @Author JuDK
 * @Date 2019/12/17 12:56
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "cadmin")
public class Admin {
    @Id
    private String id;
    // @Column(name = "username")
    private String username;
    private String password;
    private String nickname;
    /*@Transient
    private List<Admin> list;*/


}
