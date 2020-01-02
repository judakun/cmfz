package com.baizhi.cache;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName RedisAnnotation
 * @Description 类
 * @Author JuDK
 * @Date 2020/1/2 15:18
 */
@Configuration
@Aspect
public class RedisAnnotation {
    @Autowired
    private RedisTemplate redisTemplate;


    @Around("@annotation(com.baizhi.annotation.AddCache)")
    public Object arround(ProceedingJoinPoint point) throws Throwable {
        System.out.println("-------切进来了嘛-----------");
        StringBuilder str = new StringBuilder();
        //获取类名
        String className = point.getTarget().getClass().getName();
        //获取方法名
        String methodName = point.getSignature().getName();
        str.append(methodName);
        //获取参数
        Object[] args = point.getArgs();
        for (Object arg : args) {
            str.append(arg);
        }
        String s = str.toString();
        //获取缓存的key
        HashOperations hashOperations = redisTemplate.opsForHash();
        //判断缓存的hash表中是否存在该类
        Boolean aBoolean = redisTemplate.hasKey(className);
        Object result = null;
        if (aBoolean) {
            result = hashOperations.get(className, s);
            System.out.println("缓存数据已存在：" + result);
        } else {
            //获取目标函数的结果
            result = point.proceed();
            Map<Object, Object> map = new HashMap<>();
            map.put(s, result);
            hashOperations.putAll(className, map);
            //redisTemplate.expire(className,10, TimeUnit.SECONDS);
            System.out.println("缓存数据不存在：" + result);
        }
        System.out.println("------AOP结束-----");
        return result;
    }


    @After("@annotation(com.baizhi.annotation.DeleteCache)")
    public void deleteCache(JoinPoint point) {
        System.out.println("--------切进来了嘛------");
        String className = point.getTarget().getClass().getName();
        redisTemplate.delete(className);

    }
}
