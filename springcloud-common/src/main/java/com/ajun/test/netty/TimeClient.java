package com.ajun.test.netty;/*
package com.ajun.test.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

*/
/**
 * @author downing
 * @descript
 *//*

public class TimeClient {
    public void connect(int port, String host) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new TimeClentHandler());
                        }
                    });
            ChannelFuture f = b.connect(host,port).sync();
            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }

    }
    public static void main(String[] args) throws Exception {
        new TimeClient().connect(8888,"127.0.0.1");
    }
}
*/
