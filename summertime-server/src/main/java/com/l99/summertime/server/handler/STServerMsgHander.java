package com.l99.summertime.server.handler;

import com.l99.summertime.common.protocol.STReqBody;
import com.l99.summertime.common.protocol.STRespBody;
import com.l99.summertime.common.protocol.STType;
import com.l99.summertime.dao.STOfflineMsgDao;
import com.l99.summertime.entity.OfflineMsg;
import com.l99.summertime.server.controller.RMConsumer;
import com.l99.summertime.server.util.AddressUtil;
import com.l99.summertime.server.util.SocketChannelHolder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


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
    AddressUtil addressUtil;

    @Autowired
    RMConsumer rmConsumer;

    @Autowired
    STOfflineMsgDao stOfflineMsgDao;

    /**
     * 信息转发给特定客户端
     * @param channelHandlerContext
     * @param stReqBody
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, STReqBody stReqBody) throws Exception {
        // 如果是第一次登陆，则记录当前客户端和通道到 redis，使用 JSON 序列化
        redisTemplate.opsForValue().set(String.valueOf(stReqBody.getFromId()), addressUtil.getAddress());
        // 存储对应的 channel
        SocketChannelHolder.saveChannel(stReqBody.getFromId(), (Channel) channelHandlerContext.channel());
        log.info("用户{}: 登陆成功", stReqBody.getFromId());
        STRespBody stRespBody = STRespBody.newBuilder().setText("登陆成功").setFromId(stReqBody.getFromId()).build();
        channelHandlerContext.channel().writeAndFlush(stRespBody);
        // 将客户端id保存，用于离线时使用
        AttributeKey<String> name = AttributeKey.valueOf("name");
        channelHandlerContext.channel().attr(name).set(String.valueOf(stReqBody.getFromId()));
        sendOfflineMsg(channelHandlerContext, stReqBody);
    }

    protected void sendOfflineMsg(ChannelHandlerContext channelHandlerContext, STReqBody stReqBody) {
        // 读取离线信息
        List<OfflineMsg> offlineMsg = stOfflineMsgDao.getOfflineMsgByUid(stReqBody.getFromId());

        offlineMsg.forEach(msg -> {
            STRespBody stRespBody1;
            if (msg.getGroupId() == 0) {
                // 说明不是群聊
                stRespBody1 = STRespBody.newBuilder()
                        .setFromId(msg.getSenderUid())
                        .setTime(System.currentTimeMillis())
                        .setText(msg.getMsgDetail())
                        .setType(STType.CHAT_TYPE_PRIVATE)
                        .setTypeValue(STType.CHAT_TYPE_PRIVATE.getNumber())
                        .build();
            } else {
                stRespBody1 = STRespBody.newBuilder()
                        .setFromId(msg.getSenderUid())
                        .setTime(System.currentTimeMillis())
                        .setGroup(String.valueOf(msg.getGroupId()))
                        .setText(msg.getMsgDetail())
                        .setType(STType.CHAT_TYPE_PUBLIC)
                        .setTypeValue(STType.CHAT_TYPE_PUBLIC.getNumber())
                        .build();
            }
            channelHandlerContext.channel().writeAndFlush(stRespBody1).addListener(future -> {
                if (future.isSuccess()) {
                    // 确保消息发送成功,此时删除数据库中的离线消息
                    stOfflineMsgDao.deleteOfflineMsgById(msg.getId());
                }
            });
        });
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        AttributeKey<String> name = AttributeKey.valueOf("name");
        String clientId = (String) ctx.channel().attr(name).get();
        log.info("用户id{}",clientId);
        redisTemplate.delete(clientId);
        super.channelInactive(ctx);
    }
}
