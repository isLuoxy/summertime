package com.l99.summertime.server.config;

import com.l99.summertime.server.init.STChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 *
 * @author L99
 * @createDate 2019/7/28
 *
 */
@Slf4j
@Component
public class NettyServer {

    @Value("${st.server.port}")
    /** 服务器端口号 */
    private int nettyPort;

    @PostConstruct
    public void init() {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new STChannelInitializer());

        bind(serverBootstrap, nettyPort);
    }

    private void bind(ServerBootstrap bootStrap, int port) {
        bootStrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                log.info("｛｝：服务器绑定端口[｛｝ ]成功", new Date(), port);
            } else {
                log.info("服务器绑定端口失败");
            }
        });
    }

    /**
     * 用于服务器转发信息给客户端
     */
    public void sendMessage(){}
}
