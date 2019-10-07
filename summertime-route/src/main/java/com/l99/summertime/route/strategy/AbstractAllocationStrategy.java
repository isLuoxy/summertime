package com.l99.summertime.route.strategy;


import com.l99.summertime.route.util.SpringContextUtil;
import com.l99.summertime.vo.Node;
import com.l99.summertime.zookeeper.client.ZkClient;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

/**
 * 抽象类，主要实现读取存活的服务器列表，
 * @author L99
 * @createDate 2019/8/3
 *
 */
@Slf4j
public abstract class   AbstractAllocationStrategy implements AllocationStrategy {


    protected static List<Node> servers = new LinkedList<>();

    // 这里没有使用 zk 的watch 机制动态监听服务状态
    static {
        // 从 zk 中拿取存活的服务器
        ZkClient zkClient = SpringContextUtil.getBean(ZkClient.class);
        try {
            List<String> serverList = zkClient.getClient().getChildren().forPath("/st/server");
            serverList.forEach(s -> {
                String[] temp = s.split(":");
                Node node = new Node();
                node.setHost(temp[0]);
                node.setPort(Integer.valueOf(temp[1]));
                servers.add(node);
            });
            servers.stream().forEach(System.out::println);
        } catch (Exception e) {
            log.info("获取服务出错");
            e.printStackTrace();
        }
    }
}
