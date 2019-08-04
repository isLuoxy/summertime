package com.l99.summertime.server.init;

import com.l99.summertime.common.protocol.STReqBody;
import com.l99.summertime.server.handler.STServerMsgHander;
import com.l99.summertime.service.ZKService;
import io.netty.channel.ChannelInitializer;

import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author L99
 * @createDate 2019/7/28
 *
 */
@Component
public class STServerChannelInitializer extends ChannelInitializer<NioSocketChannel> {


    @Autowired
    STServerMsgHander stServerMsgHander;

    @Override
    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
        nioSocketChannel.pipeline()
                .addLast(new ProtobufVarint32FrameDecoder())
                .addLast(new ProtobufDecoder(STReqBody.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new ProtobufEncoder())
                .addLast(stServerMsgHander);
    }


}
