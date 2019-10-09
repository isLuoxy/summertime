package com.l99.summertime.client.config;

import com.l99.summertime.client.init.STClientChannelInitializer;
import com.l99.summertime.client.service.NodeService;
import com.l99.summertime.client.vo.LoginVo;
import com.l99.summertime.common.protocol.STReqBody;
import com.l99.summertime.common.protocol.STType;
import com.l99.summertime.service.ClientMsgService;
import com.l99.summertime.vo.Node;
import com.l99.summertime.vo.RequestVo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.AttributeMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @Autowired
    NodeService nodeService;

    @Reference(version = "0.0.1")
    ClientMsgService clientMsgService;

    @Autowired
    STClientChannelInitializer stClientChannelInitializer;

    /**
     * 连接服务端配置
     * @throws InterruptedException
     */
    public void connect(String userId, Node node) throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(stClientChannelInitializer);
        LoginVo.userId = "2";
        Node server = nodeService.getServer(node);
        b.connect(server.getHost(), server.getPort()).addListener(future -> {
            if (future.isSuccess()) {
                log.info("客户端连接服务器成功");
                Channel channel = ((ChannelFuture) future).channel();
                AttributeKey<String> name = AttributeKey.valueOf("name");
                channel.attr(name).set(String.valueOf(20));
                send(channel);
            }
        });
    }


    public void send(Channel channel) {
        new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().getName());
                Scanner sc = new Scanner(System.in);
                String line = sc.nextLine();

                String[] strings = line.split(" ");

                if (line.contains("create")) {
                    List<String> menmbers = new ArrayList<>();
                    for (int i = 3; i < strings.length; i++) {
                        menmbers.add(strings[i]);
                    }
                    clientMsgService.createGroup(strings[1], strings[2], menmbers);
                    continue;
                }

                RequestVo requestVo = new RequestVo();
                if (line.contains("group")) {
                    requestVo.setGroupId(Integer.valueOf(strings[1]));
                    requestVo.setFromId(Integer.valueOf(strings[2]));
                    requestVo.setP2p(false);
                    requestVo.setMsg("群发： " + new Date());
                } else if (line.contains("add")) {
                    clientMsgService.addGroup(strings[1], strings[2]);
                    continue;
                } else {
                    requestVo.setFromId(Integer.valueOf(strings[0]));
                    requestVo.setToId(Integer.valueOf(strings[1]));
                    requestVo.setP2p(true);
                    requestVo.setMsg(new Date() + "");
                }

                try {
                    if (requestVo.isP2p()) {
                        clientMsgService.sendMsg(requestVo);
                    } else {
                        clientMsgService.sendGroupMsg(requestVo);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                    log.info("客户端发送信息失败");
                }
            }
        }).start();
    }

    /**
     * 重新连接
     */
    public void reconnect() {

    }
}
