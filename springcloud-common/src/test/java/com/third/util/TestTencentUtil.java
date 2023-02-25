package com.third.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.ajun.common.WeChatPayUtil;
import com.third.domain.*;
import org.junit.jupiter.api.Test;

import javax.net.ssl.KeyManagerFactory;
import java.io.*;
import java.math.BigDecimal;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class TestTencentUtil {

    String access_token = "63_7lo0yYftfislc4Q2YyslsbeAbr7YYJD4EILko7rLbvhQbgfKQGseY-ZeF0-7w-NpWtF2tnHAZlC9ABVkCUht8RRxB-iP09z31ulHpz3V4rxTLr6vH8r-nIPAJdAOKWcAAAJFR";

    String publicAccessToken = "63_0lbatJS17cKFW09SbJxxDeFI8FplL2EExfH4mblg-l3Y-JD0ui72jQzD33I_5VUOys5sdDLs_NFCo_2NrxO1Qil8GSSZUQ3aCJtFMRSwjIPQCTOLwdD4_N-6QCoYCAbADAEIG";

    String appid = "appid";

    String mchId = "商户好";

    String ip = "真是ip";

    String openId = "用户小程序openId";

    String publicOpenId = "用户公众号id";

    String paynotifyUrl = "微信回调地址";

    String key = "商户秘钥";

    String pck12Url = "D:\\mywork\\ajun\\utils\\src\\main\\resources\\cert\\apiclient_cert.p12";

    @Test
    public void testGetUrllink(){
        UrllinkBO urllinkBO = new UrllinkBO();
        urllinkBO.setPath("pagesCommon/participateList/participateList");
        urllinkBO.setQuery("actionId=7826");
        System.out.println(TencentUtil.getUrllink(access_token,urllinkBO));
    }

    @Test
    public void testGetWxacodeunlimit(){
        WxACode aCode = new WxACode();
        aCode.setPage("pagesCommon/participateList/participateList");
        aCode.setScene("actionId=7826");
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try{
            inputStream = TencentUtil.getWxacodeunlimit(access_token, aCode);
            outputStream = new FileOutputStream(new File("D:\\test1.png"));
            byte[] buffer = new byte[1024];
            long count = 0;
            int n = 0;
            while (-1 != (n = inputStream.read(buffer))) {
                outputStream.write(buffer, 0, n);
                count += n;
            }
            outputStream.flush();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void testSendToSubscriber(){
        SubscribeBO subscribeBO = new SubscribeBO();
        subscribeBO.setAccess_token(access_token);
        subscribeBO.setTemplate_id("P_FeKzfrOi-tButoPBihC2PGJIAQPi_4wWMyCnkYEfE");
        subscribeBO.setTouser(openId);
        Map<String, Object> item = new HashMap<>();
        item.put("thing4", new SubscribeTemplate<String>("有新的金蛋"));//标题
        item.put("date2", new SubscribeTemplate<String>("2022-01-01 00:00:00"));//标题
        subscribeBO.setData(item);
        subscribeBO.setPage("pagesA/smashEgg/smashEgg?actionId=7826");
        System.out.println(TencentUtil.sendToSubscriber(subscribeBO));
    }

    @Test
    public void testSendToPublicSubscriber(){
        PublicSubscribeBO publicSubscribeBO = new PublicSubscribeBO();
        Map<String, SubscribeTemplate> map = new HashMap<>();
        publicSubscribeBO.setTouser(publicOpenId);
        publicSubscribeBO.setUrl("pagesCommon/activityDetail/activityDetail?actionId=1");
        publicSubscribeBO.setTemplate_id("O-RoC52m-QHGsX-GvT0LWGwuai4LQX9qDb9sMWqLhHw");
        map.put("first", new SubscribeTemplate<String>("first"));
        map.put("keyword1", new SubscribeTemplate<String>("keyword1"));//活动标题
        map.put("keyword2", new SubscribeTemplate<String>("keyword2"));//活动时间
        map.put("keyword3", new SubscribeTemplate<String>("keyword3"));//活动地址
        map.put("remark", new SubscribeTemplate<String>("remark" ));//备注
        publicSubscribeBO.setData(map);
        System.out.println(TencentUtil.sendToPublicSubscriber(publicAccessToken,publicSubscribeBO));
    }

    @Test
    public void testGetPublicUserInfo(){
        System.out.println(TencentUtil.getPublicUserInfo(publicAccessToken,publicOpenId));
    }

    @Test
    public void testGetPublicUserList(){
        System.out.println(TencentUtil.getPublicUserList(publicAccessToken,publicOpenId));
    }

    @Test
    public void testMsgSecCheck(){
        System.out.println(TencentUtil.msgSecCheck(access_token,"hhhhh"));
    }

    @Test
    public void mediaCheckAsync(){
        System.out.println(TencentUtil.mediaCheckAsync(access_token,"https://img-blog.csdnimg.cn/03ff789b4acc424eb77d9ecc2e21e06f.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBA5p2A5omL5LiN5aSq5Ya377yB,size_20,color_FFFFFF,t_70,g_se,x_16",1));
    }

    @Test
    public void testUnifiedOrder(){
        PayOderBO payOderBO = new PayOderBO();
        payOderBO.setOut_trade_no(RandomUtil.randomString(32));
        payOderBO.setAppid(appid);
        payOderBO.setMch_id(mchId);
        payOderBO.setBody("微信小程序支付");
        payOderBO.setDetail("微信小程序下单");
        payOderBO.setNonce_str(RandomUtil.randomString(32));
        payOderBO.setSign_type("MD5");
//        需要传过来的参数
        Integer money = 1;
        payOderBO.setOpenid(openId);
        payOderBO.setSpbill_create_ip(ip);
        payOderBO.setTotal_fee(Math.round(money.floatValue()*100));
        payOderBO.setNotify_url("");
        payOderBO.setTrade_type("JSAPI");

        UnifiedOrderVO unifiedOrderVO = TencentUtil.unifiedOrder(payOderBO,key);

        System.out.println(unifiedOrderVO);
    }

    @Test
    public void testOrderQuery(){
        OrderQueryBO orderQueryBO = new OrderQueryBO();
        orderQueryBO.setOut_trade_no("zrjlj9ge4b2ieox27qxwhnnnz3ldziga");

        orderQueryBO.setAppid(appid);
        orderQueryBO.setMch_id(mchId);
        orderQueryBO.setNonce_str(RandomUtil.randomString(32));
        orderQueryBO.setSign_type("MD5");
        //需要传过来的参数
//        orderQueryDo.setOut_trade_no(out_trade_no);


        System.out.println(TencentUtil.orderQuery(orderQueryBO,key));
    }

    @Test
    public void refund() throws KeyStoreException, IOException, NoSuchAlgorithmException, UnrecoverableKeyException, CertificateException, KeyManagementException {
        //发起退款
        RefundBO refundBO = new RefundBO();
        refundBO.setAppid(appid);
        refundBO.setMch_id(mchId);
        refundBO.setNonce_str(RandomUtil.randomString(32));
        refundBO.setSign_type("MD5");


        //需要传过来的参数
        refundBO.setOut_refund_no(RandomUtil.randomString(32));//商户退款单号
        refundBO.setOut_trade_no("zrjlj9ge4b2ieox27qxwhnnnz3ldziga");//商户订单号
        refundBO.setTotal_fee(new BigDecimal("1").multiply(new BigDecimal(100)).intValue());
        refundBO.setRefund_fee(new BigDecimal("1").multiply(new BigDecimal(100)).intValue());
        refundBO.setRefund_desc("计划有变");
        refundBO.setNotify_url(paynotifyUrl);//退款url
        refundBO.setSign(WeChatPayUtil.sign(refundBO,key,"sign"));

        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        File file = new File("");
        InputStream inputStream = new FileInputStream(file);
        keyStore.load(inputStream, refundBO.getMch_id().toCharArray());
        // 初始化密钥库
        KeyManagerFactory factory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        factory.init(keyStore, refundBO.getMch_id().toCharArray());


        System.out.println(TencentUtil.refund(refundBO,key,factory));

    }

    @Test
    public void testRefundQuery(){
        RefundQueryBO refundQueryBO = new RefundQueryBO();
        refundQueryBO.setAppid(appid);
        refundQueryBO.setMch_id(mchId);
        refundQueryBO.setNonce_str(RandomUtil.randomString(32));
        refundQueryBO.setSign_type("MD5");
        //需要传过来的参数
        refundQueryBO.setOut_trade_no("BATEST1601119936569282560");
        refundQueryBO.setSign(WeChatPayUtil.sign(refundQueryBO,key,"sign"));

        System.out.println(TencentUtil.refundQuery(refundQueryBO));
    }

    @Test
    public void testTransfers() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        TransfersBO transfersBO = new TransfersBO();
        transfersBO.setMch_appid(appid);
        transfersBO.setMchid(mchId);
        transfersBO.setNonce_str(RandomUtil.randomString(32));

        transfersBO.setCheck_name("NO_CHECK");
        //需要传过来的参数
        transfersBO.setPartner_trade_no(RandomUtil.randomString(32));
        transfersBO.setOpenid(openId);
        transfersBO.setAmount(new BigDecimal("1").intValue());
        transfersBO.setDesc("收款");
        transfersBO.setSign(WeChatPayUtil.sign(transfersBO,key,"sign"));

        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        File file = new File(pck12Url);
        InputStream inputStream = new FileInputStream(file);
        keyStore.load(inputStream, transfersBO.getMchid().toCharArray());
        // 初始化密钥库
        KeyManagerFactory factory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        factory.init(keyStore, transfersBO.getMchid().toCharArray());

        System.out.println(TencentUtil.transfers(transfersBO,factory));
    }

    @Test
    public void testRefundCallBack() throws IOException {
        TencentUtil.refundCallBack(null,key);
    }
}
