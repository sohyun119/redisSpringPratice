package com.example.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    // redis에서 쓰던 명령어를 redisTemplate를 사용해 주고 받기.

    private final ValueOperations<String, Integer> ops;
    public ArticleController(RedisTemplate<String, Integer> redisTemplate) {
        ops = redisTemplate.opsForValue();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void read(
        @PathVariable("id") Long id
    ){
        ops.increment("article:%d".formatted(id));
    }



}
