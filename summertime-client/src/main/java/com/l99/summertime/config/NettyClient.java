package com.l99.summertime.config;

import com.l99.summertime.init.STClientChannelInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;


/**
 *
 * @author L99
 * @createDate 2019/7/29
 *
 */
public class NettyClient {

    public void open() {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootStrap = new Bootstrap();

        bootStrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new STClientChannelInitializer());
    }

    private void connect(Bootstrap bootstrap, String host, int port) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 连接成功，启动控制台线程……");
                Channel channel = ((ChannelFuture) future).channel();
                while (!Thread.interrupted()) {
                    System.out.println("请输入发送到服务器的信息：");
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();
                    channel.writeAndFlush(line);
                }
            }
        });
    }
}
