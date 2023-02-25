package com.ajun.test.bio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author downing
 * @descript
 */
public class NIOServerSocketHandler implements Runnable{

    private  Socket socket;
    public NIOServerSocketHandler(Socket socket){
        this.socket = socket;
    }

    public void run() {
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(this.socket.getOutputStream());
            String currentTime = null;
            String body = null;
            while (true){
                body = bufferedReader.readLine();
                if(body == null)break;
                System.out.println("The time Server receive oder:" + body);
                currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body)?new java.util.Date(System.currentTimeMillis()).toString():"BAD ORDER";
                printWriter.print(currentTime);
            }
        } catch (IOException e) {
            e.printStackTrace();
            if(bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if(printWriter != null){
                printWriter.close();
            }
            if(this.socket != null){
                try {
                    this.socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                this.socket = null;
            }
        }
    }
}
