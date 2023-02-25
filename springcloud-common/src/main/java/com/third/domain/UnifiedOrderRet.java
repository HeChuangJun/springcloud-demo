package com.third.domain;


import lombok.Data;

@Data
public class UnifiedOrderRet {
    /**
     * 返回状态码：此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断：SUCCESS/FAIL
     */
    private String return_code;

    /**
     * 返回信息：返回信息，如非空，为错误原因，例如：签名失败、参数格式校验错误
     */
    private String return_msg;

    /**
     * 公众账号ID：调用接口提交的公众账号ID
     */
    private String appid;

    /**
     * 商户号：调用接口提交的商户号
     */
    private String mch_id;

    /**
     * 设备号：自定义参数，可以为请求支付的终端设备号等
     */
    private String device_info;

    /**
     * 随机字符串：微信返回的随机字符串
     */
    private String nonce_str;

    /**
     * 签名：微信返回的签名值，详见签名算法
     */
    private String sign;

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
     * 交易类型：交易类型，取值为：JSAPI，NATIVE，APP等，说明详见参数规定
     */
    private String trade_type;

    /**
     * 预支付交易会话标识：微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
     */
    private String prepay_id;

    /**
     * 二维码链接：trade_type为NATIVE时有返回，用于生成二维码，展示给用户进行扫码支付
     */
    private String code_url;


    @Override
    public String toString() {
        return "UnifiedOrderRet{" +
                "return_code='" + return_code + '\'' +
                ", return_msg='" + return_msg + '\'' +
                '}';
    }

}
