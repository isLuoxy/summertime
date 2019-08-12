package com.l99.summertime.client.handler;

import com.l99.summertime.common.protocol.STReqBody;
import com.l99.summertime.common.protocol.STRespBody;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

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
}
