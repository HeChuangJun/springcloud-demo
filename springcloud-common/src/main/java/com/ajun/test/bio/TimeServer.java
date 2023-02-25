package com.ajun.test.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.SocketSecurityException;

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
            while (true){//阻塞
                socket = serverSocket.accept();
                new Thread(new TimeServerHandler(socket)).start();
            }
        } finally {
            if(serverSocket != null){
                serverSocket.close();
                serverSocket = null;
            }
        }
    }
}
