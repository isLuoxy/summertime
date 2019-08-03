package com.l99.summertime.route.strategy;

import com.l99.summertime.vo.Node;

/**
 * 分配策略接口
 * @author L99
 * @createDate 2019/8/3
 *
 */
public interface AllocationStrategy {

    /**
     * 获取连接服务器
     * @param node
     * @return
     */
    Node getServer(Node node);
}
