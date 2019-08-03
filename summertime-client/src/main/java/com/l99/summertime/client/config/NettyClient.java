package com.l99.summertime.client.config;

import com.l99.summertime.client.handler.FirstClientHandler;
import com.l99.summertime.client.init.STClientChannelInitializer;
import com.l99.summertime.common.protocol.STReqBody;
import com.l99.summertime.common.protocol.STRespBody;
import com.l99.summertime.common.protocol.STType;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.Scanner;


/**
 *
 * @author L99
 * @createDate 2019/7/29
 *
 */
@Slf4j
@Component
public class NettyClient {


    @Value("${st.server.host}")
    public String host;

    @Value("${st.server.port}")
    public int port;

    /**
     * 连接服务端配置
     * @throws InterruptedException
     */
    public void connect() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new STClientChannelInitializer());

        b.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                log.info("客户端连接服务器成功");
                send(((ChannelFuture) future).channel());
            }
        });
    }


    public void send(Channel channel) {
        new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName());
                Scanner sc = new Scanner(System.in);
                String line = sc.nextLine();

                STReqBody stReqBody = STReqBody.newBuilder()
                        .setFromId(Integer.valueOf(line))
                        .setToId(1)
                        .setTypeValue(1)
                        .setType(STType.CHAT_TYPE_LOGIN)
                        .build();
                System.out.println(stReqBody);
                channel.writeAndFlush(stReqBody);
            }
        }).start();
    }
}
