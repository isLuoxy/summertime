package com.l99.summertime.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 服务器对象
 * @author L99
 * @createDate 2019/8/3
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ip
     */
    private String host;

    /**
     * 端口
     */
    private int port;
}
