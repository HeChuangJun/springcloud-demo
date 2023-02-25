package com.ajun.test.bio2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author downing
 * @descript
 */
public class NIOServer {

    public static void main(String[] args) {
        int port = 6666;
        ServerSocket serverSocket = null;
        int maxPoolSize = 50;
        int queueSize = 10000;
        try {
            serverSocket = new ServerSocket(port);
            Socket socket = null;
            System.out.println("BIOServer listening...");
            ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),maxPoolSize,120L, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(queueSize));
            while (true){
                socket = serverSocket.accept();
                executorService.submit(new NIOServerSocketHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(serverSocket != null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
