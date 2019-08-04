package com.l99.summertime.route.strategy;

import com.l99.summertime.vo.Node;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author L99
 * @createDate 2019/8/3
 *
 */
public class ConsistentHashingStrategy extends AbstractAllocationStrategy {

    /**
     *  存放服务器的排序集合
     */
    private static SortedMap<Integer, Node> serverSortedMap = new TreeMap<>();

    static {
        servers.forEach(node -> {
            int hash = getHash(node.getHost());
            serverSortedMap.put(hash, node);
        });
    }

    @Override
    public Node getServer(Node node) {
        // 当前节点的hash值
        int hash = getHash(node.getHost());
        // 所有大于该节点hash值的所有map
        SortedMap<Integer, Node> subMap = serverSortedMap.tailMap(hash);
        return subMap.get(subMap.firstKey());
    }


    /**
     * 使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
     */
    private static int getHash(String str) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < str.length(); i++) {
            hash = (hash ^ str.charAt(i)) * p;
        }
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        return hash;
    }


}
