package com.ajun.windows;


import java.awt.*;
import java.io.*;
import java.net.URI;
import java.util.concurrent.TimeUnit;

//进程工具类
public class ProcesstUtils {

    private static boolean isNotBlank(String str){
        if(str==null||str.replaceAll(" ","").equals("")){
            return false;
        }
        return true;
    }
    /**
     * @desc 启动进程
     * @param uri 应用程序的绝对路径
     */
    public static void startProc(String uri) {
        System.out.println("启动应用程序：" + uri);
        if (isNotBlank(uri)) {
            try {
                Desktop.getDesktop().open(new File(uri));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("应用程序：" + uri + "不存在！");
            }
        }
    }
    /**
     * @desc 启动默认浏览器浏览网站
     * @param url 网址
     */
    public static void browseUrl(String url) {
        System.out.println("访问网址：" + url);
        if (isNotBlank(url)) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("网址：" + url + "不存在！");
            }
        }
    }
    /**
     * @desc 杀死进程
     * @throws IOException
     * @param processName 任务管理器中的应用程序名
     */
    public static void killProc(String processName) throws IOException {
        System.out.println("关闭应用程序：" + processName);
        if (isNotBlank(processName)) {
            executeCmd("taskkill /F /IM " + processName);
        }
    }
    /**
     * @desc 执行cmd命令
     * @throws IOException
     * @param command cmd命令字符串
     */
    public static String executeCmd(String command) throws IOException {
        System.out.println("Execute command : " + command);
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("cmd /c" + command);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
        String line = null;
        StringBuilder build = new StringBuilder();
        //TODO 存在线程阻塞问题。。。。未解决
        while ((line = br.readLine()) != null) {
            build.append(line);
        }
        try {
            System.out.println(process.waitFor(1, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return build.toString();
    }
    /**
     * @desc 判断进程是否开启
     * @date 2018-3-29
     */
    public static boolean findProcess(String processName) {
        BufferedReader bufferedReader = null;
        try {
            Process proc = Runtime.getRuntime().exec("tasklist -fi " + '"' + "imagename eq " + processName +'"');
            bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(processName)) {
                    return true;
                }
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception ex) {}
            }
        }
    }

    public static void main(String[] args) {
        ProcesstUtils.startProc("C:/Program Files (x86)/Google/Chrome/Application/chrome.exe");

        try {
            ProcesstUtils.executeCmd("C:\\emqttd\\bin\\emqttd start");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("exit");


        try {
            ProcesstUtils.executeCmd("start C:\\Users\\创军\\Desktop\\chrome.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(ProcesstUtils.findProcess("cmd.exe"));

        ProcesstUtils.browseUrl("www.baidu.com");


    }
}

