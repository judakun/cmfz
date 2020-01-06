package com.baizhi.shiro;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Power;
import com.baizhi.entity.Role;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName MyRealm
 * @Description 类
 * @Author JuDK
 * @Date 2020/1/6 15:15
 */

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private AdminDao adminDao;

    @Override //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = null;
        //查询数据库，获取当前用户具有的角色和权限
        Admin admin = adminDao.queryOne(primaryPrincipal);
        System.out.println("--------------------------------------");
        if (admin != null) {
            List<Role> roles = admin.getRoles();
            Set<String> set = new HashSet<>();
            authorizationInfo = new SimpleAuthorizationInfo();
            for (Role role : roles) {
                authorizationInfo.addRole(role.getName());
                List<Power> powers = role.getPowers();
                for (Power power : powers) {
                    set.add(power.getName());
                }
            }
            //避免权限的重复
            if (set.size() != 0) {
                for (String s : set) {
                    authorizationInfo.addStringPermission(s);
                }
            }
        }
        return authorizationInfo;
    }

    @Override //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        Admin admin = adminDao.selectOne(new Admin().setUsername(principal));
        SimpleAuthenticationInfo authenticationInfo = null;
        if (admin != null) {
            authenticationInfo = new SimpleAuthenticationInfo(admin.getUsername(), admin.getPassword(), this.getName());
        }
        return authenticationInfo;
    }
}
