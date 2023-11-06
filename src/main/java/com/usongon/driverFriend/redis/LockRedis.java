package com.usongon.driverFriend.redis;
import com.usongon.driverFriend.common.utils.UuidUtil;
import com.usongon.driverFriend.enums.EResponseCode;
import com.usongon.driverFriend.framework.exception.BusinessException;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Service
@Log4j2
public class LockRedis {
    @Resource(name = "cacheRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    private static final Long DEFAULT_LOCK_SECOND = 30L;
    private static final String LOCK_PREFIX = "LOCK:";

    /**
     * 分布式锁执行
     * @param key
     * @param supplier
     * @param <T>
     * @return
     */
    public <T> T process(String key, Supplier<T> supplier) {
        return process(key, DEFAULT_LOCK_SECOND, supplier);
    }


    /**
     * 分布式锁执行
     * @param key
     * @param seconds
     * @param supplier
     * @param <T>
     * @return
     */
    public <T> T process(String key, long seconds, Supplier<T> supplier) {
        key = LOCK_PREFIX + key;
        String identity = UuidUtil.randomUUID();
        boolean errorHappened = false;
        if (tryLock(key, identity, seconds)) {
            try {
                return supplier.get();
            } catch (Exception e) {
                errorHappened = true;
                throw e;
            } finally {
                if (!releaseLock(key, identity)) {
                    log.warn("Redis分布式锁过期:{}", key);
                    if (!errorHappened) {
                        throw new BusinessException(EResponseCode.LockExpired, "锁过期:" + key, "");
                    }
                }
            }
        } else {
            throw new BusinessException(EResponseCode.NoLock, "未获取到锁", "");
        }
    }

    /**
     *
     * @param key
     * @param seconds
     * @return
     */
    private boolean tryLock(String key, String identity, long seconds) {
        Boolean b = redisTemplate.opsForValue().setIfAbsent(key, identity, seconds, TimeUnit.SECONDS);
        return b != null && b;
    }


    /**
     * 释放锁
     * @param key
     */
    private boolean releaseLock(String key, String identity){
        log.info("release lock:{key:{}, identity:{}}", key, identity);
        // 注意!!! redis中保存的带有双引号的字符串,所以这里处理一下!!!
        final String argv = "\"" + identity + "\"";
        // Lua脚本
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Long r = redisTemplate.execute((RedisConnection connect) ->
            connect.eval(script.getBytes(), ReturnType.INTEGER,
                    1,
                    key.getBytes(),
                    argv.getBytes())
        );

        return r > 0;
    }
}
