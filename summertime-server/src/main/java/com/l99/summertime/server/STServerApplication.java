package com.l99.summertime.server;

import com.l99.summertime.server.config.NettyServer;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 服务端启动类
 * @author L99
 * @createDate 2019/7/28
 *
 */
@SpringBootApplication
@EnableDubbo
public class STServerApplication implements CommandLineRunner {


    @Autowired
    NettyServer nettyServer;

    public static void main(String[] args) {
        SpringApplication.run(STServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        nettyServer.init();
    }
}
