package com.third.domain;

import lombok.Data;

@Data
public class RefundQueryBO {

    private String appid;//公众账号ID	appid	是	String(32)	wx8888888888888888	微信支付分配的公众账号ID（企业号corpid即为此appid）

    private String mch_id;//商户号	mch_id	是	String(32)	1900000109	微信支付分配的商户号

    private String nonce_str;//随机字符串	nonce_str	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位。推荐随机数生成算法

    private String sign;//签名	sign	是	String(32)	C380BEC2BFD727A4B6845133519F3AD6	签名，详见签名生成算法

    private String sign_type;//签名类型	sign_type	否	String(32)	HMAC-SHA256	签名类型，目前支持HMAC-SHA256和MD5，默认为MD5

    //private String transaction_id;//微信订单号	transaction_id	四选一	String(32)	1217752501201407033233368018	微信订单号查询的优先级是： refund_id > out_refund_no > transaction_id > out_trade_no

    private String out_trade_no;//商户订单号	out_trade_no	String(32)	1217752501201407033233368018	商户系统内部订单号，要求32个字符内（最少6个字符），只能是数字、大小写字母_-|*且在同一个商户号下唯一。详见商户订单号

    //private String out_refund_no;//商户退款单号	out_refund_no	String(64)	1217752501201407033233368018	商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。

    //private String refund_id;//微信退款单号	refund_id	String(32)	1217752501201407033233368018 微信生成的退款单号，在申请退款接口有返回

    //private String offset;//偏移量	offset	否	int	15 偏移量，当部分退款次数超过10次时可使用，表示返回的查询结果从这个偏移量开始取记录
}
