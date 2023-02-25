package com.third.domain;

import lombok.Data;

@Data
public class RefundQueryRet {
    private String result_code;//业务结果	result_code	是	String(16)	SUCCESS SUCCESS/FAIL SUCCESS退款申请接收成功，退款结果以退款状态为准 FAIL
    private String err_code;//错误码	err_code	是	String(32)	SYSTEMERROR	错误码详见第6节
    private String err_code_des;//错误描述	err_code_des	是	String(128)	系统错误	结果信息描述
    private String appid;//公众账号ID	appid	是	String(32)	wx8888888888888888	微信分配的公众账号ID（企业号corpid即为此appid）
    private String mch_id;//商户号	mch_id	是	String(32)	1900000109	微信支付分配的商户号
    private String nonce_str;//随机字符串	nonce_str	是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位
    private String sign;//签名	sign	是	String(32)	C380BEC2BFD727A4B6845133519F3AD6	签名，详见签名算法
    private Integer total_refund_count;//订单总退款次数	total_refund_count	否	int	35	订单总共已发生的部分退款次数，当请求参数传入offset后有返回
    private String transaction_id;//微信订单号	transaction_id	是	String(32)	1217752501201407033233368018	微信订单号
    private String out_trade_no;//商户订单号	out_trade_no	是	String(32)	1217752501201407033233368018	商户系统内部订单号，要求32个字符内（最少6个字符），只能是数字、大小写字母_-|*且在同一个商户号下唯一。详见商户订单号
    private Integer total_fee;//订单金额	total_fee	是	int	100	订单总金额，单位为分，只能为整数，详见支付金额
    private Integer settlement_total_fee;//应结订单金额	settlement_total_fee	否	int	100	当订单使用了免充值型优惠券后返回该参数，应结订单金额=订单金额-免充值优惠券金额。
    private String fee_type;//货币种类	fee_type	否	String(8)	CNY	订单金额货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    private Integer cash_fee;//现金支付金额	cash_fee	是	int	100	现金支付金额，单位为分，只能为整数，详见支付金额
    private Integer refund_count;//退款笔数	refund_count	是	int	1	当前返回退款笔数
    //private String out_refund_no_$n;//商户退款单号	out_refund_no_$n	是	String(64)	1217752501201407033233368018	商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
    //private String refund_id_$n;//微信退款单号	refund_id_$n	是	String(32)	1217752501201407033233368018	微信退款单号退款渠道	refund_channel_$n	否	String(16)	ORIGINALORIGINAL—原路退款BALANCE—退回到余额OTHER_BALANCE—原账户异常退到其他余额账户OTHER_BANKCARD—原银行卡异常退到其他银行卡

    //private String refund_fee_$n;//申请退款金额	refund_fee_$n	是	int	100	退款总金额,单位为分,可以做部分退款
    private Integer refund_fee;//退款总金额	refund_fee	是	int	100	各退款单的退款金额累加
    private Integer coupon_refund_fee;//代金券退款总金额	coupon_refund_fee	是	int	100	各退款单的代金券退款金额累加
    //private Integer settlement_refund_fee_$n;//退款金额	settlement_refund_fee_$n	否	int	100	退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
    //private String coupon_type_$n_$m;//代金券类型	coupon_type_$n_$m	否	String(8)	CASHCASH--充值代金券NO_CASH---非充值优惠券 开通免充值券功能，并且订单使用了优惠券后有返回（取值：CASH、NO_CASH）。$n为下标,$m为下标,从0开始编号，举例：coupon_type_$0_$1

    //private String coupon_refund_fee_$n;//总代金券退款金额	coupon_refund_fee_$n	否	int	100	代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠
    //private String coupon_refund_count_$n;//退款代金券使用数量	coupon_refund_count_$n	否	int	1	退款代金券使用数量 ,$n为下标,从0开始编号
    //private String coupon_refund_id_$n_$m;//退款代金券ID	coupon_refund_id_$n_$m	否	String(20)	10000 	退款代金券ID, $n为下标，$m为下标，从0开始编号
    //private String coupon_refund_fee_$n_$m;//单个代金券退款金额	coupon_refund_fee_$n_$m	否	int	100	单个退款代金券支付金额, $n为下标，$m为下标，从0开始编号
    //private String refund_status_$n;//退款状态	refund_status_$n	是	String(16)	SUCCESS 退款状态：SUCCESS—退款成功REFUNDCLOSE—退款关闭，指商户发起退款失败的情况。PROCESSING—退款处理中CHANGE—退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往商户平台（pay.weixin.qq.com）-交易中心，手动处理此笔退款。$n为下标，从0开始编号。

    //private String refund_account_$n;//退款资金来源	refund_account_$n	否	String(30)	REFUND_SOURCE_RECHARGE_FUNDS REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款/基本账户REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款$n为下标，从0开始编号。

    //private String refund_recv_accout_$n;//退款入账账户	refund_recv_accout_$n	是	String(64)	招商银行信用卡0403	取当前退款单的退款入账方1）退回银行卡：{银行名称}{卡类型}{卡尾号}2）退回支付用户零钱:支付用户零钱3）退还商户:商户基本账户 商户结算银行账户4）退回支付用户零钱通:支付用户零钱通

    //private String refund_success_time_$n;//退款成功时间	refund_success_time_$n	否	String(20)	2016-07-25 15:26:26	退款成功时间，当退款状态为退款成功时有返回。$n为下标，从0开始编号。
    private Integer cash_refund_fee;//用户退款金额	cash_refund_fee	是	int	90退款给用户的金额，不包含所有优惠券金额
}
