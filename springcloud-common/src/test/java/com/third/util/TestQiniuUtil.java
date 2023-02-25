package com.third.util;

import com.qiniu.processing.OperationManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.util.Auth;
import com.third.domain.GifBO;
import org.junit.jupiter.api.Test;

import java.io.*;

public class TestQiniuUtil {
    private static String accessKey = "Tbw4ukA5GvhkyUMpT-00ek9u050FzqeExNNzNRPH";

    private static String secretKey = "fk07LyduocckXjro6JGjV1dKqRXdMYTJAXDEX-O4";

    private static String bucketName = "banzhuhscl2021";

    //设置转码的队列  参考：https://developer.qiniu.com/dora/kb/3853/how-to-create-audio-and-video-processing-private-queues
    private static String persistentPipeline = "videowatermark";

    private static String url = "https://bzstatic.udinovo.com/1610366470.55gp%3D0.jpg";

    private static String videoUrl = "https://bzstatic.udinovo.com/2313.mp4";

    @Test
    public void testGetImageInfo(){
        System.out.println(QiniuUtil.getImageInfo(url));
    }

    @Test
    public void testGetVideoInfo(){
        System.out.println(QiniuUtil.getVideoInfo(videoUrl));
    }

    @Test
    public void testUploadFile() throws IOException {
        Auth auth = Auth.create(accessKey, secretKey);
        String token = auth.uploadToken(bucketName);
        File file = new File("D:\\test.png");
        System.out.println(QiniuUtil.uploadFile(new FileInputStream(file).readAllBytes(), "user/my.jpg", token));
    }

    @Test
    public void testVideoToGif(){
        Auth auth = Auth.create(accessKey, secretKey);
        GifBO gifBO = new GifBO();
        gifBO.setUrl(videoUrl);
        gifBO.setBuketName(bucketName);
        gifBO.setOrignFileName(videoUrl.replaceAll("https://bzstatic.udinovo.com/",""));
        gifBO.setSaveAsName("user/my.gif");
        gifBO.setPersistentPipeline(persistentPipeline);
        gifBO.setAuth(auth);
        System.out.println(QiniuUtil.videoToGif(gifBO));
    }



}

