package com.ajun.test.aio2;

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
public class TimeServer{

    public static void main(String[] args) throws IOException{
        int port = 6666;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            Socket socket = null;
            System.out.println("BIOServer listening...");
            ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),50,120L, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10000));
            while (true){//阻塞
                socket = serverSocket.accept();
                executorService.execute(new TimeServerHandler(socket));
            }
        } finally {
            if(serverSocket != null){
                serverSocket.close();
                serverSocket = null;
            }
        }
    }
}
