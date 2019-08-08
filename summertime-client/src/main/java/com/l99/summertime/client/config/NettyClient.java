package com.l99.summertime.client.config;

import com.l99.summertime.client.handler.FirstClientHandler;
import com.l99.summertime.client.init.STClientChannelInitializer;
import com.l99.summertime.common.protocol.STReqBody;
import com.l99.summertime.common.protocol.STRespBody;
import com.l99.summertime.common.protocol.STType;
import com.l99.summertime.service.ClientMsgService;
import com.l99.summertime.service.ZKService;
import com.l99.summertime.vo.Node;
import com.l99.summertime.vo.RequestVo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.Date;
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


    @Reference(version = "0.0.1")
    ZKService zkService;

    @Reference(version = "0.0.1")
    ClientMsgService clientMsgService;

    /**
     * 连接服务端配置
     * @throws InterruptedException
     */
    public void connect(Node node) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new STClientChannelInitializer());

        Node server = getServer(node);
        b.connect(server.getHost(), server.getPort()).addListener(future -> {
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

               if (line.equals("login")) {
                    STReqBody stReqBody = STReqBody.newBuilder()
                            .setFromId(Integer.valueOf(1))
                            .setToId(1)
                            .setTypeValue(1)
                            .setText(line)
                            .setType(STType.CHAT_TYPE_LOGIN)
                            .build();
                    System.out.println("login" + stReqBody);
                    channel.writeAndFlush(stReqBody);
                    continue;
                }

                System.out.println();
                RequestVo requestVo = new RequestVo();
                requestVo.setFromId(1);
                requestVo.setToId(1);
                requestVo.setP2p(true);
                requestVo.setMsg(new Date() + "");

                try {
                    clientMsgService.sendMsg(requestVo);
                } catch (Exception e) {
                    System.out.println(e);
                    log.info("客户端发送信息失败");
                }
            }
        }).start();
    }

    /**
     * 获取服务列表
     * @param node
     * @return
     */
    private Node getServer(Node node) {
        return zkService.consume(node);
    }
}
