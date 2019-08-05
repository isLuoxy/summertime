package com.l99.summertime.route.service.impl;

import com.l99.summertime.route.ServerDistributor;
import com.l99.summertime.route.strategy.ConsistentHashingStrategy;
import com.l99.summertime.route.zk.ServerDistributorFactory;
import com.l99.summertime.service.ZKService;
import com.l99.summertime.vo.Node;
import com.l99.summertime.zookeeper.client.ZkClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 服务注册和发现类
 * @author L99
 * @createDate 2019/8/4
 *
 */
@Service(version = "0.0.1")
@Component
@Slf4j
public class ZKServiceImpl implements ZKService {

    @Autowired
    ZkClient zkClient;

    @Override
    public void register(String path) {
        zkClient.register(path);
        log.info("服务器注册成功，地址:{}", path);
    }

    @Override
    public Node consume(Node node) {
        ServerDistributor serverDistributor = ServerDistributorFactory.builder().allocationStrategy(new ConsistentHashingStrategy()).build();
        Node server = serverDistributor.getServer(node);
        return server;
    }
}
