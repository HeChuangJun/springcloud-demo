package com.ajun.test.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author downing
 * @descript
 */
public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel,AIOSocketServer> {

    @Override
    public void completed(AsynchronousSocketChannel result, AIOSocketServer attachment) {
        attachment.asynchronousServerSocketChannel.accept(attachment,this);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        result.read(byteBuffer,byteBuffer,new ReadCompletionHandler(result));
    }

    @Override
    public void failed(Throwable exc, AIOSocketServer attachment) {
        attachment.countDownLatch.countDown();
    }
}
