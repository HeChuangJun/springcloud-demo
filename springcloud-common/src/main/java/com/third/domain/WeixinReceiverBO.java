package com.third.domain;

import lombok.Data;

@Data
public class WeixinReceiverBO {
    /**
     * 微信分配的公众账号ID（企业号corpid即为此appId）,不为空
     */
    private String appid;
    /**
     * 微信支付分配的商户号
     */
    private String mch_id;
    /**
     * 微信支付分配的终端设备号
     */
    private String device_info;
    /**
     * 随机字符串，不长于32位
     */
    private String nonce_str;
    /**
     * 签名，详见签名算法
     */
    private String sign;
    /**
     * 签名类型，HMAC-SHA256 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
     */
    private String sign_type;
    /**
     * 业务结果，SUCCESS SUCCESS/FAIL
     */
    private String result_code;
    /**
     * 结果
     */
    private String return_code;
    /**
     * 错误代码，SYSTEMERROR 错误返回的信息描述
     */
    private String err_code;
    /**
     * 错误代码描述，系统错误，错误返回的信息描述
     */
    private String err_code_des;
    /**
     * 用户标识，用户在商户appid下的唯一标识
     */
    private String openid;
    /**
     * 是否关注公众账号：Y 用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
     */
    private String is_subscribe;
    /**
     * 交易类型：JSAPI、NATIVE、APP
     */
    private String trade_type;
    /**
     * 付款银行：CMC 银行类型，采用字符串类型的银行标识，银行类型见银行列表
     */
    private String bank_type;
    /**
     * 订单金额，订单总金额，单位为分，类型：int
     */
    private Integer total_fee;
    /**
     * 应结订单金额，应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
     */
    private String settlement_total_fee;
    /**
     * 货币种类，货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     */
    private String fee_type;
    /**
     * 现金支付金额，现金支付金额订单现金支付金额，详见支付金额
     */
    private String cash_fee;
    /**
     * 现金支付货币类型，货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     */
    private String cash_fee_type;
    /**
     * 代金券金额，代金券金额<=订单金额，订单金额-代金券金额=现金支付金额，详见支付金额，类型：int
     */
    private String coupon_fee;
    /**
     * 代金券使用数量，代金券使用数量，类型：int
     */
    private String coupon_count;
    /**
     * 微信支付订单号，微信支付订单号
     */
    private String transaction_id;
    /**
     * 商户订单号，商户系统的订单号，与请求一致。
     */
    private String out_trade_no;
    /**
     * 商家数据包，原样返回
     */
    private String attach;
    /**
     * 支付完成时间
     */
    private String time_end;

    private String coupon_fee_0;

    private String coupon_id_0;
}
