package com.l99.summertime.route;

import com.l99.summertime.route.strategy.AllocationStrategy;
import com.l99.summertime.vo.Node;
import lombok.Data;

/**
 * 服务器分发器
 * @author L99
 * @createDate 2019/8/3
 *
 */
@Data
public class ServerDistributor {

    private AllocationStrategy serverAllocationStrategy;

    public ServerDistributor(AllocationStrategy serverAllocationStrategy) {
        this.serverAllocationStrategy = serverAllocationStrategy;
    }

    /**
     * 获取服务器列表
     * @param node
     * @return
     */
    public Node getServer(Node node) {
        return serverAllocationStrategy.getServer(node);
    }
}
