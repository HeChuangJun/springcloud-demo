package com.third.util;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.processing.OperationManager;
import com.qiniu.processing.OperationStatus;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringUtils;
import com.qiniu.util.UrlSafeBase64;
import com.third.domain.GifBO;
import com.third.domain.ImageInfo;
import com.third.domain.UploadRet;

public class QiniuUtil {

    /**
     * 获得图片信息
     * @param url
     * @return
     */
    public static ImageInfo getImageInfo(String url){
        return JSONUtil.toBean(HttpUtil.get(url+"?imageInfo"),ImageInfo.class);
    }

    /**
     * 获得图片信息 https://developer.qiniu.com/dora/1247/audio-and-video-metadata-information-avinfo
     * @param url
     * @return
     */
    public static JSONObject getVideoInfo(String url){
        return JSONUtil.parseObj(HttpUtil.get(url+"?avinfo"));
    }

    /**
     * 上传文件到七牛
     * @param bytes
     * @param key
     * @param token
     * @return JSONObject key hash
     */
    public static UploadRet uploadFile(byte[] bytes,String key,String token){
        try {
            //调用put方法上传
            UploadManager uploadManager = new UploadManager(new Configuration());
            //返回体：res.bodyString()
            Response res = uploadManager.put(bytes, key, token);
            //打印返回的信息
            if (res.statusCode == 200) {
                //200为上传成功
                return JSONUtil.toBean(res.bodyString(), UploadRet.class);
            }
        } catch (QiniuException e) {
            Response r = e.response;
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 视频转gif
     */
    public static String videoToGif(GifBO gifBO) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());

        //新建一个OperationManager对象
        OperationManager operationManager = new OperationManager((Auth) gifBO.getAuth(), cfg);

        StringBuilder sb = new StringBuilder();
        sb.append("avthumb/gif/ss/0/t/3/r/5|saveas/");
        sb.append(UrlSafeBase64.encodeToString(gifBO.getBuketName() + ":" + gifBO.getSaveAsName()));

        //将多个数据处理指令拼接起来
        String persistentOpfs = StringUtils.join(new String[]{
                sb.toString()
        }, ";");

        //设置pipeline参数
        try {
            String persistentId = operationManager.pfop(gifBO.getBuketName(), gifBO.getOrignFileName(),
                    persistentOpfs, gifBO.getPersistentPipeline(), "https://test.udinovo.com/banzhu/test", true);
            //可以根据该 persistentId 查询任务处理进度
            int i = 5;
            while (i>0){
                OperationStatus operationStatus = operationManager.prefop(persistentId);
                i--;
                if(operationStatus.code == 0){
                    break;
                }
                try {
                    Thread.sleep(8000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return gifBO.getSaveAsName();
        } catch (QiniuException e) {
            //捕获异常信息
            Response r = e.response;
            // 请求失败时简单状态信息
            System.out.println(r.toString());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得首帧
     */
    public static String getFirstFrame(String videoUrl){
        return videoUrl+"?vframe/jpg/offset/0";
    }
}
