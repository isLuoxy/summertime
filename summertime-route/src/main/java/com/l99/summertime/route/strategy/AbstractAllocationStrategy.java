package com.l99.summertime.route.strategy;


import com.l99.summertime.vo.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象类，主要实现读取存活的服务器列表，
 * @author L99
 * @createDate 2019/8/3
 *
 */
public abstract class AbstractAllocationStrategy implements AllocationStrategy {

    protected static List<Node> servers;

    static {
        // 从 zk 中拿取存活的服务器
        servers = new ArrayList<>();

    }
}
