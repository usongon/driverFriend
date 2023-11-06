package com.usongon.driverFriend.redis;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.usongon.driverFriend.bean.session.LoginSession;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
public class SessionRedis {
    @Resource(name = "sessionRedisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置session
     * @param key
     * @param session
     */
    public void setSession(String key, LoginSession session) {
        redisTemplate.opsForValue().set(key, session, Duration.ofHours(2));
    }

    /**
     * 设置session
     * @param key
     * @param session
     */
    public void setSessionByIm(String key, LoginSession session) {
        redisTemplate.opsForValue().set(key, session, Duration.ofDays(15));
    }

    /**
     * 获取session
     * @param key
     * @return
     */
    public LoginSession getSession(String key) {
        return (LoginSession) redisTemplate.opsForValue().get(key);
    }


    /**
     * 延长过期时间
     * @param key
     */
    public void setExpire(String key) {
        redisTemplate.expire(key, 2, TimeUnit.HOURS);
    }

    /**
     * 删除key
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

}
