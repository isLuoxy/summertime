package com.l99.summertime.route;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 *
 * @author L99
 * @createDate 2019/8/4
 *
 */
@MapperScan("com.l99.summertime.dao")
@SpringBootApplication
@EnableDubbo
public class STRouteApplication {

    public static void main(String[] args) {
        SpringApplication.run(STRouteApplication.class, args);
    }
}
