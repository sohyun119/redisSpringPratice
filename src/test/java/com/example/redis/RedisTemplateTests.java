package com.example.redis;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class RedisTemplateTests {

    // java -> redis 데이터 타입이 무엇인지
    // java에서 쓰는 것이" 문자열"이며, redis의 어떤 타입도 조작이 가능하다.
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void StringOpsTest(){
        // 문자열 조작을 위한 클래스
        ValueOperations<String, String> ops
            // 지금 RedisTempplate에 설정된 타입을 바탕으로
            // Redis 문자열을 조작을 할거다.
            = stringRedisTemplate.opsForValue();

        ops.set("simplekey", "simplevalue");
        System.out.println(ops.get("simplekey"));

        // 집합을 조작하기 위한 클래스
        SetOperations<String, String>  setOps
             = stringRedisTemplate.opsForSet();
        setOps.add("hobbies", "games");
        setOps.add("hobbies", "coding", "alcohol", "games");

        System.out.println(setOps.size("hobbies"));

        stringRedisTemplate.expire("hobbies", 10, TimeUnit.SECONDS);
        stringRedisTemplate.delete("simplekey");
    }

    @Autowired
    private RedisTemplate<String, ItemDto> itemRedisTemplate;

    @Test
    public void itemRedisTemplateTest(){
        ValueOperations<String, ItemDto> ops
            = itemRedisTemplate.opsForValue();
        ops.set("my:keyboard", ItemDto.builder()
            .name("Mechanical Keyboard")
            .price(250000)
            .description("OMG")
            .build()
        );
        System.out.println(ops.get("my:keyboard"));

        ops.set("my:mouse", ItemDto.builder()
            .name("mouce mice")
            .price(10000)
            .description("OMG")
            .build()
        );
        System.out.println(ops.get("my:mouse"));
    }



}
