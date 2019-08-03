package com.l99.summertime.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 服务器对象
 * @author L99
 * @createDate 2019/8/3
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node {

    /**
     * ip
     */
    private String host;

    /**
     * 端口
     */
    private int port;
}
