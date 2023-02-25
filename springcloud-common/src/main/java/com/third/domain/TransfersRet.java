package com.third.domain;

import lombok.Data;

@Data
public class TransfersRet {
    /**
     * 返回状态码：此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断：SUCCESS/FAIL
     */
    private String return_code;

    /**
     * 返回信息：返回信息，如非空，为错误原因，例如：签名失败、参数格式校验错误
     */
    private String return_msg;

    /**
     * 业务结果：SUCCESS/FAIL
     */
    private String result_code;

    /**
     * 错误代码：详细参见下文错误列表
     */
    private String err_code;

    /**
     * 错误代码描述：错误信息描述
     */
    private String err_code_des;

    /**
     * 公众账号ID：调用接口提交的公众账号ID
     */
    private String mch_appid;

    /**
     * 商户号：调用接口提交的商户号
     */
    private String mchid;

    /**
     * 微信支付分配的终端设备号
     */
    private String device_info;

    /**
     * 微信支付分配的终端设备号
     */
    private String nonce_str;


    /**
     * 商户订单号，需保持历史全局唯一性(只能是字母或者数字，不能包含有其它字符)
     */
    private String partner_trade_no;


    /**
     * 企业付款成功，返回的微信付款单号
     */
    private String payment_no;


    /**
     * 企业付款成功时间
     */
    private String payment_time;


}
