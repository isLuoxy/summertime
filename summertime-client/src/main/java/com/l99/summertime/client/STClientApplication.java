package com.l99.summertime.client;


import com.l99.summertime.client.config.NettyClient;
import com.l99.summertime.service.ZKService;
import com.l99.summertime.vo.Node;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.util.Random;


/**
 *
 * @author L99
 * @createDate 2019/7/29
 *
 */
@SpringBootApplication
@EnableDubbo
public class STClientApplication implements CommandLineRunner {


    @Autowired
    NettyClient nettyClient;

    public static void main(String[] args) {
        SpringApplication.run(STClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Node node = new Node();
        node.setPort(8002);
        node.setHost(InetAddress.getLocalHost().getHostAddress());
        nettyClient.connect(String.valueOf(new Random().nextInt()), node);
    }
}
