package com.l99.summertime.service;

import com.l99.summertime.vo.Node;

import java.util.List;

/**
 * zk 服务接口
 * @author L99
 * @createDate 2019/8/4
 *
 */
public interface ZKService {

    /**
     * 服务注册
     * @param path 服务注册的路径
     */
    void register(String path);

    /**
     * 服务消费
     * @param node 客户端节点
     */
    Node consume(Node node);
}
