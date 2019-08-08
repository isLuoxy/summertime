package com.l99.summertime.server.handler;

import com.l99.summertime.common.protocol.STReqBody;
import com.l99.summertime.common.protocol.STRespBody;
import com.l99.summertime.common.protocol.STType;
import com.l99.summertime.server.config.AddressConfig;
import com.l99.summertime.server.util.SocketChannelHolder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author L99
 * @createDate 2019/7/30
 *
 */
@Slf4j
@Component
@ChannelHandler.Sharable
public class STServerMsgHander extends SimpleChannelInboundHandler<STReqBody> {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    AddressConfig addressConfig;

    /**
     * 信息转发给特定客户端
     * @param channelHandlerContext
     * @param stReqBody
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, STReqBody stReqBody) throws Exception {
        // 如果是第一次登陆，则记录当前客户端和通道到 redis，使用 JSON 序列化
        if (stReqBody.getType() == STType.CHAT_TYPE_LOGIN) {
            redisTemplate.opsForValue().set(String.valueOf(stReqBody.getToId()), addressConfig.getAddress());
            // 存储对应的 channel
            SocketChannelHolder.saveChannel(stReqBody.getFromId(), (Channel) channelHandlerContext.channel());
            log.info("用户{} 登陆成功", stReqBody.getToNick());
            STRespBody stRespBody = STRespBody.newBuilder().setText("登陆成功").setFromId(stReqBody.getToId()).build();
            channelHandlerContext.channel().writeAndFlush(stRespBody);
        }
    }

}
