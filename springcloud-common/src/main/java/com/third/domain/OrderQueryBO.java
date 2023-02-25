package com.third.domain;

import lombok.Data;

@Data
public class OrderQueryBO {

    private String appid;//公众账号ID	appid	是	String(32)	wxd678efh567hg6787	微信支付分配的公众账号ID（企业号corpid即为此appid）

    private String mch_id;//商户号	mch_id	是	String(32)	1230000109	微信支付分配的商户号

    //private String appid;//微信订单号	transaction_id	二选一	String(32)	1009660380201506130728806387	微信的订单号，建议优先使用

    private String out_trade_no;//商户订单号	out_trade_no	String(32)	20150806125346	商户系统内部订单号，要求32个字符内（最少6个字符），只能是数字、大小写字母_-|*且在同一个商户号下唯一。详见商户订单号

    private String nonce_str;//随机字符串	nonce_str	是	String(32)	C380BEC2BFD727A4B6845133519F3AD6	随机字符串，不长于32位。推荐随机数生成算法

    private String sign;//签名	sign	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	通过签名算法计算得出的签名值，详见签名生成算法

    private String sign_type;//签名类型	sign_type	否	String(32)	HMAC-SHA256	签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
}
