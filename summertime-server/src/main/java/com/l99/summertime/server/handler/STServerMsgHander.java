package com.l99.summertime.server.handler;

import com.l99.summertime.common.protocol.STReqBody;
import com.l99.summertime.common.protocol.STRespBody;
import com.l99.summertime.common.protocol.STType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *
 * @author L99
 * @createDate 2019/7/30
 *
 */
@Slf4j
@Component
public class STServerMsgHander extends SimpleChannelInboundHandler<STReqBody> {

//    @Autowired
//    RedisTemplate<String, Object> redisTemplate;

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
            // redisTemplate.opsForValue().set(String.valueOf(stReqBody.getToId()), (NioSocketChannel) channelHandlerContext.channel());
            log.info("用户{} 登陆成功", stReqBody.getToNick());
        } else {
            channelHandlerContext.channel().writeAndFlush(new Date());
        }
    }
}
