package com.l99.summertime;

import com.l99.summertime.client.STClientApplication;
import com.l99.summertime.service.ZKService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLOutput;

/**
 *
 * @author L99
 * @createDate 2019/8/2
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = STClientApplication.class)
public class test {

    @Reference(version = "0.0.1")
    public ZKService zkService;

}
