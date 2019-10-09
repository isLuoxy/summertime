package com.l99.summertime.client.handler;

import com.l99.summertime.common.protocol.STReqBody;
import com.l99.summertime.common.protocol.STRespBody;
import com.l99.summertime.common.protocol.STType;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.data.Id;
import org.springframework.stereotype.Component;

/**
 * 客户端消息处理
 * @author L99
 * @createDate 2019/7/30
 *
 */
@Slf4j
@ChannelHandler.Sharable
@Component
public class STClientMsgHandler extends SimpleChannelInboundHandler<STRespBody> {

    STClientMsgHandler() {
        super(false);
    }

    /**
     *  接收到消息，进行前端展示
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, STRespBody stRespBody) throws Exception {

        // 转发给前端，这里前端需要轮询信息，然后将信息
        if (stRespBody.getType() == STType.CHAT_TYPE_PRIVATE) {
            log.info("收到信息{} ：来自{}", stRespBody.getText(), stRespBody.getFromId());
        }
        if (stRespBody.getType() == STType.CHAT_TYPE_PUBLIC) {
            log.info("收到群组 {} 信息:{},来自{},", stRespBody.getGroup(), stRespBody.getText(), stRespBody.getFromId());
        }
        channelHandlerContext.fireChannelRead(stRespBody);
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


}
