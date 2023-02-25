package com.third.domain;

import lombok.Data;

/**
 * @author downing
 * @descript 微信支付回调实体类
 */
@Data
public class RefundReceiverBO2 {

    private String return_code;//返回状态码	return_code	是	String(16)	SUCCESS SUCCESS/FAIL 此字段是通信标识，表示接口层的请求结果，并非退款状态。返回信息	return_msg	是	String(128)	OK 当return_code为FAIL时返回信息为错误原因 ，例如签名失败
    private String result_code;//result_code	是	String(16)	SUCCESSSUCCESS/FAIL SUCCESS退款申请接收成功，结果通过退款查询接口查询 FAIL 提交业务失败
    private String err_code;//错误代码	err_code	否	String(32)	SYSTEMERROR	列表详见错误码列表

    private String err_code_des;//错误代码描述	err_code_des	否	String(128)	系统超时	结果信息描述

    private String appid;//公众账号ID	appid	是	String(32)	wx8888888888888888	微信分配的公众账号ID

    private String mch_id;//商户号	mch_id	是	String(32)	1900000109	微信支付分配的商户号

    private String nonce_str;//随机字符串	nonce_str	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位

    private String sign;//签名	sign	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	签名，详见签名算法

    private String transaction_id;//微信支付订单号	transaction_id	是	String(32)	4007752501201407033233368018	微信订单号

    private String out_trade_no;//商户订单号	out_trade_no	是	String(32)	33368018	商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。

    private String out_refund_no;//商户退款单号	out_refund_no	是	String(64)	121775250	商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。

    private String refund_id;//微信退款单号	refund_id	是	String(32)	2007752501201407033233368018	微信退款单号

    private Integer refund_fee;//退款金额	refund_fee	是	Int	100	退款总金额,单位为分,可以做部分退款

    //@XStreamAlias("settlement_refund_fee")
    //private Integer settlement_refund_fee;//应结退款金额	settlement_refund_fee	否	Int	100	去掉非充值代金券退款金额后的退款金额，退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额

    private Integer total_fee;//标价金额	total_fee	是	Int	100	订单总金额，单位为分，只能为整数，详见支付金额

    private Integer settlement_total_fee;//应结订单金额	settlement_total_fee	否	Int	100	去掉非充值代金券金额后的订单总金额，应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。

    //@XStreamAlias("fee_type")
    //private String fee_type;//标价币种	fee_type	否	String(8)	CNY	订单金额货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型

    private Integer cash_fee;//现金支付金额	cash_fee	是	Int	100	现金支付金额，单位为分，只能为整数，详见支付金额

    //@XStreamAlias("cash_fee_type")
    //private String cash_fee_type;//现金支付币种	cash_fee_type	否	String(16)	CNY	货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型

    //@XStreamAlias("cash_refund_fee")
    //private Integer cash_refund_fee;//现金退款金额	cash_refund_fee	否	Int	100	现金退款金额，单位为分，只能为整数，详见支付金额

   // @XStreamAlias("coupon_type_0")
    //private String coupon_type_0;//代金券类型	coupon_type_$n	否	String(8)	CASH CASH--充值代金券 NO_CASH---非充值代金券 订单使用代金券时有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_0
    //@XStreamAlias("coupon_refund_fee")
    //private String coupon_refund_fee;//代金券退款总金额	coupon_refund_fee	否	Int	100	代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠
    //@XStreamAlias("coupon_refund_fee_0")
    //private String coupon_refund_fee_0;//单个代金券退款金额	coupon_refund_fee_$n	否	Int	100	代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠

    //@XStreamAlias("coupon_refund_count")
    //private String coupon_refund_count;//退款代金券使用数量	coupon_refund_count	否	Int	1	退款代金券使用数量
    //@XStreamAlias("coupon_refund_id_0")
    //private String coupon_refund_id_0;//退款代金券ID	coupon_refund_id_$n	否	String(20)	10000 	退款代金券ID, $n为下标，从0开始编号

    private String req_info;
}
