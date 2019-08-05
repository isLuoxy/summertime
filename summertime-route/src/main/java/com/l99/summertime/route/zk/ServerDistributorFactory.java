package com.l99.summertime.route.zk;

import com.l99.summertime.route.ServerDistributor;
import com.l99.summertime.route.strategy.AllocationStrategy;

/**
 * 通过 zk 获取服务器列表，并根据一定的策略分配服务器
 * @author L99
 * @createDate 2019/8/3
 *
 */
public class ServerDistributorFactory {


    public static ServerDistributorFactory.Builder builder() {
        return new ServerDistributorFactory.Builder();
    }


    public static class Builder {
        private AllocationStrategy allocationStrategy;

        private Builder() {
            // 默认情况下采用一致性哈希策略
            // this.allocationStrategy = new ConsistentHashingStrategy();
        }


        public ServerDistributorFactory.Builder allocationStrategy(AllocationStrategy allocationStrategy) {
            this.allocationStrategy = allocationStrategy;
            return this;
        }

        public ServerDistributor build() {
            ServerDistributor serverDistributor = new ServerDistributor();
            serverDistributor.setServerAllocationStrategy(allocationStrategy);
            return serverDistributor;
        }
    }
}
