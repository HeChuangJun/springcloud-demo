package com.third.domain;

import lombok.Data;

@Data
public class RefundBO {

    private String appid;//公众账号ID	appid	是	String(32)	wx8888888888888888	微信分配的公众账号ID（企业号corpid即为此appId）

    private String mch_id;//商户号	mch_id	是	String(32)	1900000109	微信支付分配的商户号

    private String nonce_str;//随机字符串	nonce_str	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位。推荐随机数生成算法

    private String notify_url;//退款结果通知url	notify_url	否	String(256)	https://weixin.qq.com/notify/异步接收微信支付退款结果通知的回调地址，通知URL必须为外网可访问的url，不允许带参数如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效。


    private String sign;//签名	sign	是	String(32)	C380BEC2BFD727A4B6845133519F3AD6	签名，详见签名生成算法

    private String sign_type;//签名类型	sign_type	否	String(32)	HMAC-SHA256	签名类型，目前支持HMAC-SHA256和MD5，默认为MD5

    private String transaction_id;//微信支付订单号	transaction_id	二选一	String(32)	1217752501201407033233368018	微信生成的订单号，在支付通知中有返回

    private String out_trade_no;//商户订单号	out_trade_no	String(32)	1217752501201407033233368018	商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。transaction_id、out_trade_no二选一，如果同时存在优先级：transaction_id> out_trade_no

    private String out_refund_no;//商户退款单号	out_refund_no	是	String(64)	1217752501201407033233368018	商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。

    private Integer total_fee;//订单金额	total_fee	是	Int	100	订单总金额，单位为分，只能为整数，详见支付金额

    private Integer refund_fee;//退款金额	refund_fee	是	Int	100	退款总金额，订单总金额，单位为分，只能为整数，详见支付金额

    private String refund_fee_type;//退款货币种类	refund_fee_type	否	String(8)	CNY	退款货币类型，需与支付一致，或者不填。符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型

    private String refund_desc;//退款原因	refund_desc	否	String(80)	商品已售完 若商户传入，会在下发给用户的退款消息中体现退款原因//注意：若订单退款金额≤1元，且属于部分退款，则不会在退款消息中体现退款原因

    private String refund_account;//退款资金来源	refund_account	否	String(30)	REFUND_SOURCE_RECHARGE_FUNDS 仅针对老资金流商户使用 REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款） REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款

    //电子发票入口开放标识	receipt	否	String(8)	Y	Y，传入Y时，支付成功消息和支付详情页将出现开票入口。需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效
    //private String receipt;

    //是否需要分账	profit_sharing	否	String(16)	Y	Y-是，需要分账 N-否，不分账 字母要求大写，不传默认不分账
    //private String profit_sharing;

    //+场景信息	scene_info	否	String(256) {"store_info" : { "id": "SZTX001", "name": "腾大餐厅", "area_code": "440305", "address": "科技园中一路腾讯大厦" }}该字段常用于线下活动时的场景信息上报，支持上报实际门店信息，商户也可以按需求自己上报相关信息。该字段为JSON对象数据，对象格式为{"store_info":{"id": "门店ID","name": "名称","area_code": "编码","address": "地址" }} ，字段详细说明请点击行前的+展开
    //private String scene_info;
}
