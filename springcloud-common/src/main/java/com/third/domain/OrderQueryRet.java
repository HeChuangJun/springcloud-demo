package com.third.domain;


import lombok.Data;

@Data

public class OrderQueryRet {

    private String return_code;//	返回状态码	是	String(16)	SUCCESS SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看trade_state来判断

    private String return_msg;//返回信息		是	String(128)	OK 当return_code为FAIL时返回信息为错误原因 ，例如 签名失败参数格式校验错误

    //以下字段在return_code为SUCCESS的时候有返回

    private String appid;//公众账号ID		是	String(32)	wxd678efh567hg6787	微信分配的公众账号ID

    private String mch_id;//商户号		是	String(32)	1230000109	微信支付分配的商户号

    private String nonce_str;//随机字符串		是	String(32)	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位。推荐随机数生成算法

    private String sign;//签名	sign	是	String(32)	C380BEC2BFD727A4B6845133519F3AD6	签名，详见签名生成算法

    private String result_code;//业务结果		是	String(16)	SUCCESS	SUCCESS/FAIL

    private String err_code;//错误代码		否	String(32)	SYSTEMERROR	当result_code为FAIL时返回错误代码，详细参见下文错误列表

    private String err_code_des;//错误代码描述		否	String(128)	系统错误	当result_code为FAIL时返回错误描述，详细参见下文错误列表
    //以下字段在return_code 、result_code、trade_state都为SUCCESS时有返回 。交易成功判断条件： return_code、result_code和trade_state都为SUCCESS

    private String device_info;// 设备号		否	String(32)	013467007045764	微信支付分配的终端设备号

    private String openid;//用户标识	openid	是	String(128)	oUpF8uMuAJO_M2pxb1Q9zNjWeS6o	用户在商户appid下的唯一标识

    private String is_subscribe;//是否关注公众账号	is_subscribe	是	String(1)	Y	用户是否关注公众账号，Y-关注，N-未关注

    private String trade_type;// 交易类型	trade_type	是	String(16)	JSAPI	调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，MICROPAY，详细说明见参数规定

    private String trade_state;//交易状态	trade_state	是	String(32)	SUCCESS	SUCCESS--支付成功REFUND--转入退款NOTPAY--未支付CLOSED--已关闭REVOKED--已撤销(刷卡支付)USERPAYING--用户支付中PAYERROR--支付失败(其他原因，如银行返回失败)ACCEPT--已接收，等待扣款

    private String bank_type;//付款银行	bank_type	是	String(32)	CMC	银行类型，采用字符串类型的银行标识

    private Integer total_fee;//标价金额	total_fee	是	int	100	订单总金额，单位为分

    private Integer settlement_total_fee;//应结订单金额	settlement_total_fee	否	int	100	当订单使用了免充值型优惠券后返回该参数，应结订单金额=订单金额-免充值优惠券金额。

    private String fee_type;//标价币种	fee_type	否	String(8)	CNY	货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型

    private Integer cash_fee;//现金支付金额	cash_fee	是	int	100	现金支付金额订单现金支付金额，详见支付金额

    private String cash_fee_type;//现金支付币种	cash_fee_type	否	String(16)	CNY	货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型

    //private Integer coupon_fee;//代金券金额	coupon_fee	否	int	100	“代金券”金额<=订单金额，订单金额-“代金券”金额=现金支付金额，详见支付金额

    //private Integer coupon_count;//代金券使用数量	coupon_count	否	int	1	代金券使用数量 代金券类型	coupon_type_$n	否	String	CASH CASH：充值代金券 NO_CASH：非充值优惠券 开通免充值券功能，并且订单使用了优惠券后有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_$0

    //private String coupon_id_$n;//代金券ID	coupon_id_$n	否	String(20)	10000 	代金券ID, $n为下标，从0开始编号

    //private String coupon_fee_$n;//单个代金券支付金额	coupon_fee_$n	否	int	100	单个代金券支付金额, $n为下标，从0开始编号

    private String transaction_id;//微信支付订单号	transaction_id	是	String(32)	1009660380201506130728806387	微信支付订单号

    private String out_trade_no;//商户订单号	out_trade_no	是	String(32)	20150806125346	商户系统内部订单号，要求32个字符内（最少6个字符），只能是数字、大小写字母_-|*且在同一个商户号下唯一。详见商户订单号

    private String attach;//附加数据	attach	否	String(128)	深圳分店	附加数据，原样返回

    private String time_end;//支付完成时间	time_end	是	String(14)	20141030133525	订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则

    private String trade_state_desc;//交易状态描述	trade_state_desc	是	String(256)	支付失败，请重新下单支付	对当前查询订单状态的描述和下一步操作的指引


}
