package com.ajun.test.nio;

import java.io.IOException;

/**
 * @author downing
 * @descript
 */
public class TimeServer {
    public static void main(String[] args) throws IOException {
        int port = 6666;
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer,"NIO-MultplexerTimeServer-001").start();
    }
}
