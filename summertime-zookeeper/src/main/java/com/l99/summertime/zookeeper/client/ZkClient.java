package com.l99.summertime.zookeeper.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;


/**
 * zookeeper 客户端
 * @author L99
 * @createDate 2019/8/3
 *
 */
@Data
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class ZkClient {

    private CuratorFramework client;
    private String zookeeperServer;
    private int sessionTimeoutMs;
    private int connectionTimeoutMs;
    private int baseSleepTimeMs;
    private int maxRetries;

    /**
     * 初始化 zk 客户端
     */
    public void init() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(baseSleepTimeMs, maxRetries);
        client = CuratorFrameworkFactory.builder().connectString(zookeeperServer)
                .retryPolicy(retryPolicy)
                .sessionTimeoutMs(sessionTimeoutMs)
                .connectionTimeoutMs(connectionTimeoutMs)
                .build();
    }

    /**
     * 打开 zk 客户端
     */
    public void start() {
        init();
        client.start();
    }

    public void stop() {
        client.close();
    }

    /**
     * 获取客户端
     * @return
     */
    public CuratorFramework getClient() {
        return client;
    }

    /**
     * 注册到 zk 的地址
     * @param path
     */
    public void register(String path) {
        try {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("服务器注册出错");
        }
    }
}
