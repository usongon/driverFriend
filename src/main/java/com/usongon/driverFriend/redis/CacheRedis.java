package com.usongon.driverFriend.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class CacheRedis {
    @Resource(name = "cacheRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 写值
     * @param key
     * @param value
     * @param duration
     */
    public void setValue(String key, Object value, Duration duration) {
        redisTemplate.opsForValue().set(key, value, duration);
    }

    /**
     * 取值
     * @param key
     * @return Object
     */
    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取过期时间
     * @param key
     * @param timeUnit
     * @return
     */
    public Long getExpire(String key, TimeUnit timeUnit) {
        return redisTemplate.getExpire(key, timeUnit);
    }

    /**
     * 设置过期时间
     * @param key
     * @param timeout
     * @param timeUnit
     */
    public void expire(String key, long timeout, TimeUnit timeUnit) {
        redisTemplate.expire(key, timeout, timeUnit);
    }


    /**
     * 删除key
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }


    /**
     * 生成自增ID
     * @param key
     * @param timeout
     * @param timeUnit
     * @return
     */
    public long generateIncrementId(String key,  long timeout, TimeUnit timeUnit) {
        RedisAtomicLong entityIdCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
        long increment = entityIdCounter.getAndIncrement();
        entityIdCounter.expire(timeout, timeUnit);
        return increment;
    }
}
