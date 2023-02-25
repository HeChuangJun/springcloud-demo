package com.third.util;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.net.SSLContextBuilder;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.ssl.SSLSocketFactoryBuilder;
import cn.hutool.json.JSONUtil;
import com.ajun.common.WeChatPayUtil;
import com.third.domain.*;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

public class TencentUtil {

    //微信小程序审核图片
    private static final String img_sec_check = "https://api.weixin.qq.com/wxa/img_sec_check?access_token=";

    //微信小程序审核图片+音频
    private static final String media_check_async = "https://api.weixin.qq.com/wxa/media_check_async?access_token=";

    //微信小程序审核图片+音频
    private static final String msg_sec_check = "https://api.weixin.qq.com/wxa/msg_sec_check?access_token=";

    private static final String getwxacodeunlimit = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=";

    private static final String jscode2session = "https://api.weixin.qq.com/sns/jscode2session";

    private static final String accessToken = "https://api.weixin.qq.com/cgi-bin/token";

    private static final String getuserphonenumber = "https://api.weixin.qq.com/wxa/business/getuserphonenumber?access_token=";

    private static final String sendToSubscriber = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=";

    //公众号发送模板消息
    //https://developers.weixin.qq.com/doc/offiaccount/Message_Management/Template_Message_Interface.html#5
    private static final String sendToPublicSubscriber = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    //获取公众号用户信息
    //https://developers.weixin.qq.com/doc/offiaccount/User_Management/Get_users_basic_information_UnionID.html#UinonId
    private static final String getPublicUserInfo = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=";

    //微信公众号获取用户列表
    private static final String getPublicUserList = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=";

    private static final String urllink = "https://api.weixin.qq.com/wxa/generate_urllink?access_token=";

    //微信支付下单
    private static final String unifiedOrder = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    //微信支付订单查询
    private static final String  orderQuery = "https://api.mch.weixin.qq.com/pay/orderquery";

    //微信支付退款
    private static final String refund = "https://api.mch.weixin.qq.com/secapi/pay/refund";

    //微信支付退款查询
    private static final String refundQuery = "https://api.mch.weixin.qq.com/pay/refundquery";

    //微信支付企业支付
    private static final String transfers = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";




    /**
     * 小程序/公众号获取accesstoken https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/access-token/auth.getAccessToken.html
     */
    public static String getAccessToken(String appid,String grant_type,String secret){
        return HttpUtil.get(accessToken+"grant_type="+grant_type+"&appid="+appid+"&secret="+secret);
    }

    /**
     * 旧版获取手机号
     */
    public static String getOldPhone(String encryptedData,String session_key,String ivParameter){
        try {
            byte[] raw = Base64.decodeBase64(session_key);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            //IvParameterSpec iv = new IvParameterSpec(decoder.decodeBuffer(ivParameter));
            IvParameterSpec iv = new IvParameterSpec(Base64.decodeBase64(ivParameter));
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] myendicod = Base64.decodeBase64(encryptedData);
            byte[] original = cipher.doFinal(myendicod);
            return new String(original, "utf-8");
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 新版获取手机号 https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/user-info/phone-number/getPhoneNumber.html
     */
    public static String getNewPhone(String accessToken,String code){
        Map<String,Object> map = new HashMap<>();
        map.put("code",code);
        return HttpUtil.post(getuserphonenumber + accessToken, JSONUtil.toJsonStr(map));
    }

    /**
     * 微信小程序授权登录 https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html#%E8%AF%B7%E6%B1%82%E5%9C%B0%E5%9D%80
     */
    public static String getOpenId(String appid,String secret,String grant_type,String code){
        return HttpUtil.get(jscode2session + "appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code");
    }

    /**
     * 获取小程序 URL Link https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/url-link/urllink.generate.html
     */
    public static String getUrllink(String accessToken,UrllinkBO urllinkBO){
        return HttpUtil.post( urllink + accessToken,JSONUtil.toJsonStr(urllinkBO));
    }


    /**
     * 微信小程序码 https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/qr-code/wxacode.getUnlimited.html
     */
    public static InputStream getWxacodeunlimit(String accessToken,WxACode aCode) {
        //WxACode aCode = new WxACode();
        //aCode.set_hyaline(true);
        //aCode.setAccess_token(token);
        //aCode.setAuto_color(true);
        //aCode.setLine_color("{\"r\":0,\"g\":0,\"b\":0}");
        //aCode.setWidth(430);
        //前端必须穿的参数
        //aCode.setPage(path);
        //aCode.setScene(scene);
        try {
            return HttpUtil.createPost(getwxacodeunlimit + accessToken).body(JSONUtil.toJsonStr(aCode)).execute().bodyStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 微信小程序订阅消息 https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/subscribe-message/subscribeMessage.send.html
     */
    public static String sendToSubscriber(SubscribeBO subscribeBO){
        return HttpUtil.post(sendToSubscriber + subscribeBO.getAccess_token(),JSONUtil.toJsonStr(subscribeBO));
    }

    /**
     * 微信公众号订阅消息 https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/subscribe-message/subscribeMessage.send.html
     */
    public static String sendToPublicSubscriber(String accessToken,PublicSubscribeBO publicSubscribeBO){
        return HttpUtil.post(sendToPublicSubscriber + accessToken,JSONUtil.toJsonStr(publicSubscribeBO));
    }

    /**
     * 微信公众号获取用户信息 //https://developers.weixin.qq.com/doc/offiaccount/User_Management/Get_users_basic_information_UnionID.html#UinonId
     */
    public static String getPublicUserInfo(String accessToken,String publicOpenId){
        return HttpUtil.get(getPublicUserInfo + accessToken + "&openid=" + publicOpenId + "&lang=zh_CN");
    }

    /**
     *  微信公众号获取用户列表
     */
    public static String getPublicUserList(String accessToken,String next_openid){
        return HttpUtil.get(getPublicUserList + accessToken + (next_openid != null?"&next_openid="+next_openid:""));
    }

    /**
     * 小程序内容安全 https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/sec-center/sec-check/msgSecCheck.html
     */
    public static String msgSecCheck(String accessToken,String content){
        Map<String,Object> map = new HashMap<>();
        map.put("content",content);
        return HttpUtil.post(msg_sec_check + accessToken, JSONUtil.toJsonStr(map));
    }

    /**
     * 小程序图片安全 https://developers.weixin.qq.com/miniprogram/dev/OpenApiDoc/sec-center/sec-check/mediaCheckAsync.html
     */
    public static String mediaCheckAsync(String accessToken, String media_url, Integer media_type){
        Map<String,Object> map = new HashMap<>();
        map.put("media_url",media_url);
        map.put("media_type",media_type);
        return HttpUtil.post(media_check_async+accessToken,JSONUtil.toJsonStr(map));
    }

    /**
     * 微信支付下单 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1
     */
    public static UnifiedOrderVO unifiedOrder(PayOderBO payOderBO,String key){
        payOderBO.setSign(WeChatPayUtil.sign(payOderBO,key,"sign"));
        String data = WeChatPayUtil.toXml(payOderBO);
        String result = HttpUtil.post(unifiedOrder, data);

        UnifiedOrderRet orderRet = XmlUtil.xmlToBean(XmlUtil.parseXml(result).getFirstChild(),UnifiedOrderRet.class);
        //校验签名
        String nowSign = WeChatPayUtil.sign(orderRet,key,"sign");

        if(!orderRet.getSign().equalsIgnoreCase(nowSign)){
            throw new RuntimeException("[ 微信支付下单"+payOderBO.getOut_trade_no()+" ] 验证返回内容签名错误。");
        }

        if(!orderRet.getResult_code().equals("SUCCESS") && !orderRet.getReturn_msg().equals("OK")){
            throw new RuntimeException("[ 微信支付下单"+payOderBO.getOut_trade_no()+" ] 失败，结果:>{}"+orderRet);
        }

        Map<String, Object> payToken = new HashMap<>();
        payToken.put("appId", payOderBO.getAppid());
        payToken.put("timeStamp", System.currentTimeMillis() / 1000);
        payToken.put("nonceStr", orderRet.getNonce_str());
        payToken.put("package", "prepay_id=" + orderRet.getPrepay_id());
        payToken.put("signType", "MD5");

        String paySign = WeChatPayUtil.sign(payToken,key);
        UnifiedOrderVO orderVo = new UnifiedOrderVO();
        orderVo.setSign_type(String.valueOf(payToken.get("signType")));
        orderVo.setNonce_str(orderRet.getNonce_str());
        orderVo.setPrepay_id(String.valueOf(payToken.get("package")));
        orderVo.setTimestamp(String.valueOf(payToken.get("timeStamp")));
        orderVo.setSign(paySign.toUpperCase());
        orderVo.setOrderId(payOderBO.getOut_trade_no());
        return orderVo;
    }

    /**
     * 微信支付查单 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_2
     */
    public static OrderQueryRet orderQuery(OrderQueryBO orderQueryBO,String key){
        orderQueryBO.setSign(WeChatPayUtil.sign(orderQueryBO,key));
        String data = WeChatPayUtil.toXml(orderQueryBO);
        String result = HttpUtil.post(orderQuery, data);

        OrderQueryRet orderQueryRet = XmlUtil.xmlToBean(XmlUtil.parseXml(result).getFirstChild(),OrderQueryRet.class);

//        if(orderQueryRet.getReturn_code().equals("SUCCESS")
//                && orderQueryRet.getReturn_msg().equals("OK")
//                && orderQueryRet.getResult_code().equals("SUCCESS")
//                && orderQueryRet.getTrade_state().equals("SUCCESS")
//        ){
//            throw new RuntimeException("[ 微信支付订单 "+orderQueryBO.getOut_trade_no() +"错误]"+orderQueryRet.toString());
//        }
        return orderQueryRet;
    }

    /**
     * 微信退款 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_4
     */
    public static RefundRet refund(RefundBO refundBO,String key,KeyManagerFactory factory) throws NoSuchAlgorithmException, KeyManagementException {
        String data = WeChatPayUtil.toXml(refundBO);

        HttpRequest request = HttpRequest.post(refund)
                .charset(CharsetUtil.CHARSET_UTF_8)
                .disableCache()
                .setSSLSocketFactory(SSLContextBuilder.create()
                        .setKeyManagers(factory.getKeyManagers())
                        .setProtocol(SSLContextBuilder.SSL).build().getSocketFactory());
        HttpResponse response = request.body(data).execute();
        RefundRet refundRet = XmlUtil.xmlToBean(XmlUtil.parseXml(response.body()).getFirstChild(),RefundRet.class);

        if(!"SUCCESS".equals(refundRet.getReturn_code())||!"SUCCESS".equals(refundRet.getResult_code())){
            throw new RuntimeException("[ 微信支付退款"+refundBO.getOut_trade_no()+" ]状态码错误。");
        }
//        TODO 签名校验失败
//        String preSign = WeChatPayUtil.sign(refundRet,key,"sgin");
//        System.out.println("preSign="+preSign);
//        System.out.println("refundRet.getSign()="+refundRet.getSign());
//        if (!refundRet.getSign().equalsIgnoreCase(preSign)) {
//            throw new RuntimeException("[ 微信退款"+refundBO.getOut_trade_no()+" ] 验证返回内容签名错误。");
//        }
        return refundRet;
    }

    /**
     * 微信退款查询 https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_5
     */
    public static RefundQueryRet refundQuery(RefundQueryBO refundQueryBO){
        String data = WeChatPayUtil.toXml(refundQueryBO);
        String result = HttpUtil.post(refundQuery, data);
        RefundQueryRet refundQueryRet = XmlUtil.xmlToBean(XmlUtil.parseXml(result).getFirstChild(),RefundQueryRet.class);
        return refundQueryRet;
    }

    /**
     * 微信企业支付 https://pay.weixin.qq.com/wiki/doc/api/tools/mch_pay.php?chapter=14_2
     */
    public static TransfersRet transfers(TransfersBO transfersBO,KeyManagerFactory factory){
        String data = WeChatPayUtil.toXml(transfersBO);
        HttpRequest request = HttpRequest.post(transfers)
                .charset(CharsetUtil.CHARSET_UTF_8)
                .disableCache()
                .setSSLSocketFactory(SSLContextBuilder.create()
                        .setKeyManagers(factory.getKeyManagers())
                        .setProtocol(SSLContextBuilder.SSL).build().getSocketFactory());
        HttpResponse response = request.body(data).execute();
        TransfersRet transfersRet = XmlUtil.xmlToBean(XmlUtil.parseXml(response.body()).getFirstChild(),TransfersRet.class);

//        if (!"SUCCESS".equals(transfersRet.getReturn_code()) || !"SUCCESS".equals(transfersRet.getResult_code())) {
//            if(!"SYSTEMERROR".equals(transfersRet.getErr_code())){
//                throw new RuntimeException("[ 微信企业支付失败"+transfersBO.getPartner_trade_no()+" ] 请求:>"+data.replaceAll("\n", "")+", 结果:>"+ result);
//            }
//        }
        return transfersRet;
    }

    /**
     * 微信回调处理解析
     * @param request
     * @return
     * @throws IOException
     */
    public static WeixinReceiverBO wxPayCallback(HttpServletRequest request,String key) throws IOException {
        String xml = IoUtil.read(request.getReader());

        WeixinReceiverBO receiverBo = XmlUtil.xmlToBean(XmlUtil.parseXml(xml).getFirstChild(),WeixinReceiverBO.class);

        //1.签名验证
//        String sign = WeChatPayUtil.sign(receiverBo,key);
//        if (!sign.equalsIgnoreCase(receiverBo.getSign())) {
//            System.out.println("[ 微信支付 "+receiverBo.getOut_trade_no()+" ] 验证签名错误，本地生成签名:>"+sign+",接收签名:>"+receiverBo.getSign());
//            return null;
//        }

        //2.结果判断
//        if (!"SUCCESS".equals(receiverBo.getReturn_code()) || !"SUCCESS".equals(receiverBo.getResult_code())) {
//            System.out.println("[ 微信支付下单回调 "+receiverBo.getOut_trade_no()+" ]异常，请求:>"+xml.replaceAll("\n", ""));
//            return null;
//        }

        return receiverBo;
    }

    /**
     * 微信退款回调
     */
    public static RefundReceiverBO refundCallBack(HttpServletRequest request, String key) throws IOException {

//        String xml = IoUtil.read(request.getReader());
        String xml = "<xml><return_code>SUCCESS</return_code><appid><![CDATA[wx7ff5739d00ff792c]]></appid><mch_id><![CDATA[1526015311]]></mch_id><nonce_str><![CDATA[f25d150f11a90b677bc640b7f90d6729]]></nonce_str><req_info><![CDATA[i2FFdwt3/WmWflkV3qNIUSChKok5eWku+XnOeDIAKnxkE7Rv/sw+uMJiczi7PZC0u57wjg8HCg3F0jNJYU8V1g4KPnKX4YWjHTOzEldbA0uOGoAd7cACYEUgUmi0qecPvt2uh+hKIhQAmEV3I0pKD3j7iQ/RtlGNJ6mzS+4DJb8k2jK954yURS2Eia0UUDeQK3Jyah30zjC2z77wgnSYCAhdhOaoupNpB0joFHdklCEDNIMip3lofqoSYdJqlkr4PlzdHkGCGO2Qgm/8YnMUq38J1VPAh7HZe2TWw/5rZ0PiqgzbWd80VkYtjKuy1awjst+BXFACNbnvZIH9KehSLyugHivW37lKYzHCFRqM3r4TLUt/oy93hOfCcnArQNsHGfnYfK62AotupAifEP4gOktJPZNESPDen7EG2vnshMvw8WD7oDwWvRTjABd3MmdivkYNs8r4W/4JNmaUqBglmgXsBNMRo7mMPRYHw1nlS4L+dFi4GIUCWrWNiVvsL4gz0ksROoJ4A90m1CkIIIbvWTpOBSt/Jxfi7XvUAxu0ZXSFxfb8KSma0ElB+ChHQvqZZPAyrFMl7YvtBrIldJ/E4+bHDiUPB1Fn7ZxUd/oPE729QeXaImoa8O5NZ3PoQHZMz1KInlHeTuQTgc3FQinqbOtIS22ixphlycA9PA6b4iLk+el+sHIV6uQHAMpGtabJRusKJkqcceJB93fND86YXvI312I1uIoakyoGliSax3F9LWpqeKfRnBRiLQ1h+WhyXH55muq3uoTlTyUNwi/1vA190nwS2rokmRrC+dEoOEZ38j06y1n7vH2k6FNdjd5rndR4wxRpmISGHY2pTL6hSBtfM0OtUX8ClMdsP01oW5ql6CwjDAZPiG/vFjJCdd5JmXbGgGcyb0B4jDvARHwD/X1RdI300LvLBXxileU00ID+za20p29h3oP4OBLd+CqI9Bw3bfV39gSfwAzFXQ4uNzW4HLa8mO5BkVB9QaSnYi4sEPoQqPzmfvc2wUCDLdsFLG3zXzio4gNg5VE3Y2iARGeEWTpIOCrkIXpfpO1wHtc4Njwtz5wFlqpGUyfpnQL5yhPFLNLc4lnSYzB0lRldGMxaE/pJgUH+3V+gzay95YX+zfNd/zmuQxQHsa4sKA6P]]></req_info></xml>";
        RefundReceiverBO2 receiverBo2 = XmlUtil.xmlToBean(XmlUtil.parseXml(xml).getFirstChild(),RefundReceiverBO2.class);

        //2.结果判断
//        if (!"SUCCESS".equals(receiverBo2.getReturn_code())) {
//            throw new RuntimeException("[ 微信支付退款订单回调 "+receiverBo2.getOut_trade_no()+" ] 异常，结果:>"+receiverBo2.toString());
//        }
        RefundReceiverBO receiverBo = null;
        //解密
        if(!StrUtil.isEmpty(receiverBo2.getReq_info())){
            String result = getRefundDecrypt(receiverBo2.getReq_info(),key);
            receiverBo = XmlUtil.xmlToBean(XmlUtil.parseXml(result).getFirstChild(),RefundReceiverBO.class);
            receiverBo.setAppid(receiverBo2.getAppid());
            receiverBo.setMch_id(receiverBo2.getMch_id());
            receiverBo.setNonce_str(receiverBo2.getNonce_str());
        }
        //1.签名验证
//        String sign = WeChatPayUtil.sign(receiverBo,key);
//        if (!sign.equalsIgnoreCase(receiverBo.getSign())) {
//            throw new RuntimeException("[ 微信退款订单回调异常 "+receiverBo.getOut_trade_no()+" ] 验证签名错误，本地生成签名:>"+sign+",接收签名:>"+receiverBo.getSign());
//        }

        return receiverBo;
    }

    private static String getRefundDecrypt(String reqInfo, String secretKey) {
        String result = "";
        try {
            byte[] digest = MD5.create().digestHex(secretKey).toLowerCase().getBytes();
            SecretKeySpec key = new SecretKeySpec(digest, "AES");
            byte[] decode = cn.hutool.core.codec.Base64.decode(reqInfo);
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] bytes = cipher.doFinal(decode);
            result = new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
