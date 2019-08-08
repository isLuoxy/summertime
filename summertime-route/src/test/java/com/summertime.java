package com;

import com.l99.summertime.route.STRouteApplication;
import com.l99.summertime.service.ZKService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author L99
 * @createDate 2019/8/4
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = STRouteApplication.class)
public class summertime {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Test
    public void redisTest(){
        System.out.println(redisTemplate.opsForValue().get(String.valueOf(1)));
    }
}
