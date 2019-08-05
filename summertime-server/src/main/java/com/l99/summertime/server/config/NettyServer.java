package com.l99.summertime.server.config;

import com.l99.summertime.server.init.STServerChannelInitializer;
import com.l99.summertime.service.ZKService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.net.InetAddress;

/**
 *
 * @author L99
 * @createDate 2019/7/28
 *
 */
@Slf4j
@Component
public class NettyServer implements Serializable {

    @Reference(version = "0.0.1")
    ZKService zkService;

    @Value("${st.server.port}")
    /** 服务器监听端口号 */
    public int nettyPort;

    @Value("${st.server.zookeeper.path}")
    /** 服务注册的地址 */
    public String rootPath;

    @Autowired
    STServerChannelInitializer stServerChannelInitializer;

    /**
     * 初始化服务端，绑定端口
     * @throws InterruptedException
     */
    public void init() throws Exception {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(stServerChannelInitializer);

        bind(serverBootstrap);
    }

    private void bind(ServerBootstrap bootStrap) throws Exception {
        bootStrap.bind(nettyPort).addListener(future -> {
            if (future.isSuccess()) {
                log.info("服务端初始化成功,端口号:{}", nettyPort);

                // 注册到服务中心
                String host = InetAddress.getLocalHost().getHostAddress();
                String address = host + ":" + nettyPort;
                zkService.register(rootPath + address);
            }
        });
    }

    /**
     * 用于服务器转发信息给客户端
     */
    public void sendMessage() {
    }
}
