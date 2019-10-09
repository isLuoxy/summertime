package com.l99.summertime.client.init;

import com.l99.summertime.client.handler.STClientHeartBeatHandler;
import com.l99.summertime.client.handler.STClientStateHandler;
import com.l99.summertime.common.protocol.STRespBody;
import com.l99.summertime.client.handler.STClientMsgHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author L99
 * @createDate 2019/7/29
 *
 */
@Component
public class STClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    STClientStateHandler stClientStateHandler;

    @Autowired
    STClientMsgHandler stClientMsgHandler;

    @Autowired
    STClientHeartBeatHandler stClientHeartBeatHandler;

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline().addLast(new ProtobufVarint32FrameDecoder())
                .addLast(new ProtobufDecoder(STRespBody.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new ProtobufEncoder())
                .addLast(stClientStateHandler)
                .addLast(stClientMsgHandler)
                .addLast(stClientHeartBeatHandler);
    }
}
