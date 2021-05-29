package com.lengqi.common.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Component
@Slf4j
public class RedisLock {
    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 加锁
     * @param key
     * @param value 当前时间+超时时间
     * @return
     */
    public boolean lock(String key,String value){
        //加锁成功
        if (redisTemplate.opsForValue().setIfAbsent(key,value)){
            return true;
        }

        //假如currentValue=A先占用了锁  其他两个线程的value都是B,保证其中一个线程拿到锁
        String currentValue = (String) redisTemplate.opsForValue().get(key);
        //锁过期  防止出现死锁
        if (!StringUtils.isEmpty(currentValue) &&
                Long.parseLong(currentValue) < System.currentTimeMillis()){
            //获取上一步锁的时间
            String oldValue = (String) redisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue) &&
                    oldValue.equals(currentValue)){
                return true;
            }
        }

        return false;
    }

    /**
     * 解锁
     * @param key
     * @param value
     */
    public void unLock(String key,String value){
        try {
            String currentValue = (String) redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)){
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("[redis分布式锁]：解锁异常，{}",e);
        }
    }
}
