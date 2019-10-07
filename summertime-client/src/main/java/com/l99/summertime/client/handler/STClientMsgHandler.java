package com.l99.summertime.client.handler;

import com.l99.summertime.common.protocol.STReqBody;
import com.l99.summertime.common.protocol.STRespBody;
import com.l99.summertime.common.protocol.STType;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.data.Id;

/**
 * 客户端消息处理
 * @author L99
 * @createDate 2019/7/30
 *
 */
@Slf4j
public class STClientMsgHandler extends SimpleChannelInboundHandler<STRespBody> {

    /**
     *  接收到消息，进行前端展示
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, STRespBody stRespBody) throws Exception {
        // 转发给前端，这里前端需要轮询信息，然后将信息
        if (StringUtils.isEmpty(stRespBody.getGroup())) {
            log.info("收到信息{} ：来自{}", stRespBody.getText(), stRespBody.getFromId());
            return;
        }
        log.info("收到群组 {} 信息:{},来自{},", stRespBody.getGroup(), stRespBody.getText(), stRespBody.getFromId());
    }

    /**
     * 如果用户离线，则要清除记录
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端：{} 连接到服务器", ctx.channel().toString());
        super.channelActive(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;

            if (idleStateEvent.state() == IdleState.READER_IDLE) {
                // 如果客户端特定时间内没有收到信息，此时对所在服务器进行探测
                log.info("正在检测与服务端的连接");
                STReqBody stReqBody = STReqBody.newBuilder()
                        .setType(STType.CHAT_TYPE_UNKNOWN)
                        .setTypeValue(STType.CHAT_TYPE_UNKNOWN_VALUE)
                        .setTime(System.currentTimeMillis())
                        .build();
                ctx.writeAndFlush(stReqBody).addListener(future -> {
                    if (!future.isSuccess()) {
                        // 重连机制

                    }
                });
            }
        }
    }
}
