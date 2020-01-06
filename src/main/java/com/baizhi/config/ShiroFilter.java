package com.baizhi.config;

import com.baizhi.shiro.MyRealm;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ShiroFilter
 * @Description 配置类
 * @Author JuDK
 * @Date 2020/1/6 15:08
 */
@Configuration
public class ShiroFilter {
    //@Bean声明当前方法的返回值交由工厂管理
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //匿名资源要在认证资源之上 anon匿名资源 authc认证资源
        Map<String, String> map = new HashMap<>();
        map.put("/login.jsp", "anon");
        map.put("/user/adminLogin", "anon");
        map.put("/user/getCode", "anon");
        map.put("/assets/**", "anon");
        map.put("/**", "authc");
        bean.setFilterChainDefinitionMap(map);
        bean.setLoginUrl("/login.jsp");
        bean.setSecurityManager(securityManager);
        return bean;
    }

    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(MemoryConstrainedCacheManager cacheManager, MyRealm myRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setCacheManager(cacheManager);
        securityManager.setRealm(myRealm);
        return securityManager;
    }

    @Bean
    public MyRealm getMyRealm() {
        MyRealm myRealm = new MyRealm();
        return myRealm;
    }

    //创建缓存，避免每一次每一次用户角色判断都查询一次数据库
    @Bean
    public MemoryConstrainedCacheManager cacheManager() {
        MemoryConstrainedCacheManager cacheManager = new MemoryConstrainedCacheManager();
        return cacheManager;
    }
}
