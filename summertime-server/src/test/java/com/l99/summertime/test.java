package com.l99.summertime;

import com.l99.summertime.common.protocol.STReqBody;
import com.l99.summertime.common.protocol.STType;
import com.l99.summertime.server.STServerApplication;
import com.l99.summertime.server.config.RMConsumerConfig;
import com.l99.summertime.server.controller.RMConsumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author L99
 * @createDate 2019/8/2
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = STServerApplication.class)
public class test {
    @Autowired
    RedisTemplate redisTemplate;


    @Autowired
    RMConsumer rmConsumer;

    @Test
    public void load() {
        STReqBody stReqBody = STReqBody.newBuilder().setFromId(10).setToId(20).setType(STType.CHAT_TYPE_UNKNOWN).build();
        redisTemplate.opsForValue().set(123, stReqBody);
    }

    @Test
    public void consumeTest() {
        try {
            rmConsumer.listener("Message", "192.168.137.1:8000");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
