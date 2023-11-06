package com.usongon.driverFriend.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;


/**
 * redis配置
 */
@Configuration
@EnableConfigurationProperties(RedisProperties.class)
@EnableAutoConfiguration(exclude = {
        RedisAutoConfiguration.class,
        RedisRepositoriesAutoConfiguration.class}) // 禁止系统默认的redis自动配置
public class RedisConfig {

    @Resource
    private RedisProperties redisProperties;

    @Bean("sessionLettuceConnectionFactory")
    public LettuceConnectionFactory sessionLettuceConnectionFactory() {
        return generateLettuceConnectionFactory(redisProperties, 0);
    }

    @Primary
    @Bean("cacheLettuceConnectionFactory")
    public LettuceConnectionFactory cacheLettuceConnectionFactory() {
        return generateLettuceConnectionFactory(redisProperties, 1);
    }

    @Bean("sessionRedisTemplate")
    public RedisTemplate sessionRedisTemplate() {
        return generateRedisTemplate(sessionLettuceConnectionFactory());
    }

    @Primary
    @Bean("cacheRedisTemplate")
    public RedisTemplate cacheRedisTemplate() {
        return generateRedisTemplate(cacheLettuceConnectionFactory());
    }

    /**
     * 生成工厂
     * @param redisProperties
     * @return LettuceConnectionFactory
     */
    private LettuceConnectionFactory generateLettuceConnectionFactory(RedisProperties redisProperties, int database) {
        // 基本配置
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setDatabase(database);
        configuration.setHostName(redisProperties.getHost());
        configuration.setPassword(redisProperties.getPassword());
        configuration.setPort(redisProperties.getPort());

        // 连接池配置
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxIdle(redisProperties.getLettuce().getPool().getMaxIdle());
        poolConfig.setMinIdle(redisProperties.getLettuce().getPool().getMinIdle());
        poolConfig.setMaxTotal(redisProperties.getLettuce().getPool().getMaxActive());
        poolConfig.setMaxWaitMillis(redisProperties.getLettuce().getPool().getMaxWait().toMillis());

        // 生成连接池客户端配置
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder()
                .poolConfig(poolConfig)
                .commandTimeout(redisProperties.getTimeout())
                .build();

        return new  LettuceConnectionFactory(configuration, clientConfiguration);
    }

    /**
     * 生成RedisTemplate
     * @param lettuceConnectionFactory
     * @return RedisTemplate
     */
    private RedisTemplate generateRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);

        // 序列化设置
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
