package com.l99.summertime.route;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author L99
 * @createDate 2019/8/4
 *
 */
@SpringBootApplication
@EnableDubbo
public class STRouteApplication {

    public static void main(String[] args) {
        SpringApplication.run(STRouteApplication.class, args);
    }
}
