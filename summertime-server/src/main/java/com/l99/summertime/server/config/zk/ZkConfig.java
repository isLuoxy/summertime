package com.l99.summertime.server.config.zk;

import com.l99.summertime.zookeeper.client.ZkClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * zookeeper 配置,由容器进行管理
 * @author L99
 * @createDate 2019/8/3
 *
 */
@Configuration
public class ZkConfig {

    @Value("${zookeeper.server}")
    private String zookeeperServer;
    @Value(("${zookeeper.sessionTimeoutMs}"))
    private int sessionTimeoutMs;
    @Value("${zookeeper.connectionTimeoutMs}")
    private int connectionTimeoutMs;
    @Value("${zookeeper.maxRetries}")
    private int maxRetries;
    @Value("${zookeeper.baseSleepTimeMs}")
    private int baseSleepTimeMs;

    @Bean(initMethod = "start", destroyMethod = "stop")
    public ZkClient zkClient() {
        ZkClient zkClient = new ZkClient();
        zkClient.setZookeeperServer(zookeeperServer);
        zkClient.setSessionTimeoutMs(sessionTimeoutMs);
        zkClient.setConnectionTimeoutMs(connectionTimeoutMs);
        zkClient.setMaxRetries(maxRetries);
        zkClient.setBaseSleepTimeMs(baseSleepTimeMs);
        return zkClient;
    }
}
