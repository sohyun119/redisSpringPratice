package com.example.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, ItemDto> itemRedisTemplate(
        RedisConnectionFactory connectionFactory
        ){
        RedisTemplate<String, ItemDto> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.json());

        return redisTemplate;
    }

    // 정수로 가져와서 정수로 저장 가능
    @Bean
    public RedisTemplate<String, Integer> articleTemplate(
        RedisConnectionFactory redisConnectionFactory
    ){
        RedisTemplate<String, Integer> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(RedisSerializer.string());
        template.setKeySerializer(new GenericToStringSerializer<>(Integer.class));
        return template;
    }

    // Http Session 과 Session Clustering
    @Bean
    public RedisSerializer<Object> springSessionDefaultRedisSerializer(){
        return RedisSerializer.json();
    }

}
