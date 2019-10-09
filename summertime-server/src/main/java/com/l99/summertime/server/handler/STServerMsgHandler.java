package com.l99.summertime.server.handler;

import com.l99.summertime.common.protocol.STReqBody;
import com.l99.summertime.common.protocol.STRespBody;
import com.l99.summertime.common.protocol.STType;
import com.l99.summertime.dao.STOfflineMsgDao;
import com.l99.summertime.entity.OfflineMsg;
import com.l99.summertime.server.controller.RMConsumer;
import com.l99.summertime.server.util.AddressUtil;
import com.l99.summertime.server.util.ClientUtil;
import com.l99.summertime.server.util.SocketChannelHolder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
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
public class STServerMsgHandler extends SimpleChannelInboundHandler<STReqBody> {

    /**
     * 由于是继承了 SimpleChannelInboundHandler ，如果在 hanler 中传递时需要
     */
    STServerMsgHandler() {
        super(false);
    }

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
        log.info("测试进入服务器的读取信息方法");
        log.info("消息类型:{}", stReqBody.getType().getDescriptorForType());
        if (stReqBody.getType() == STType.CHAT_TYPE_LOGIN) {
            log.info("测试服务器判读登陆方法");
            // 如果是第一次登陆，则记录当前客户端和通道到 redis，使用 JSON 序列化
            redisTemplate.opsForValue().set(String.valueOf(stReqBody.getFromId()), addressUtil.getAddress());
            // 存储对应的 channel
            SocketChannelHolder.saveChannel(stReqBody.getFromId(), (Channel) channelHandlerContext.channel());
            log.info("用户{}: 登陆成功", stReqBody.getFromId());
//            STRespBody stRespBody = STRespBody.newBuilder().setText("登陆成功").setFromId(stReqBody.getFromId()).build();
//            channelHandlerContext.channel().writeAndFlush(stRespBody);
            // 将客户端id保存，用于离线时使用
            AttributeKey<String> name = AttributeKey.valueOf("name");
            channelHandlerContext.channel().attr(name).set(String.valueOf(stReqBody.getFromId()));
            sendOfflineMsg(channelHandlerContext, stReqBody);
            return;
        }
        channelHandlerContext.fireChannelRead(stReqBody);
    }


    protected void sendOfflineMsg(ChannelHandlerContext channelHandlerContext, STReqBody stReqBody) {
        // 读取离线信息
        String userId = ClientUtil.getClientId(channelHandlerContext);
        log.info("读取客户端{}离线消息", userId);
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
                    log.info("发送客户端{}离线消息", userId);
                }
            });
        });
    }

    /**
     * 客户端离线逻辑处理
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        String clientId = ClientUtil.getClientId(ctx);
        log.info("用户id{}", clientId);
        redisTemplate.delete(clientId);
        super.channelInactive(ctx);
    }
}
