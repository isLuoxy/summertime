package com.l99.summertime.client;


import com.l99.summertime.client.config.NettyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 *
 * @author L99
 * @createDate 2019/7/29
 *
 */
@SpringBootApplication
public class STClientApplication implements CommandLineRunner {


    @Autowired
    NettyClient nettyClient;

    public static void main(String[] args) {
        SpringApplication.run(STClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        nettyClient.connect();
    }


}
