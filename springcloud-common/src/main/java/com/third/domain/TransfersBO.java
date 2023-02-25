package com.third.domain;

import lombok.Data;

@Data
public class TransfersBO {

    private String mch_appid;//商户账号appid	mch_appid	是	wx8888888888888888	String(128)	申请商户号的appid或商户号绑定的appid

    private String mchid;//商户号	mchid	是	1900000109	String(32)	微信支付分配的商户号

    //private String device_info;//设备号	device_info	否	013467007045764	String(32)	微信支付分配的终端设备号

    private String nonce_str;//随机字符串	nonce_str	是	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	String(32)	随机字符串，不长于32位

    private String sign;//签名	sign	是	C380BEC2BFD727A4B6845133519F3AD6	String(32)	签名，详见签名算法

    private String partner_trade_no;//商户订单号	partner_trade_no	是	10000098201411111234567890	String(32)	商户订单号，需保持唯一性(只能是字母或者数字，不能包含有其它字符)

    private String openid;//用户openid	openid	是	oxTWIuGaIt6gTKsQRLau2M0yL16E	String(64)	商户appid下，某用户的openid

    private String check_name;//校验用户姓名选项	check_name	是	FORCE_CHECK	String(16)	NO_CHECK：不校验真实姓名 FORCE_CHECK：强校验真实姓名

    //private String re_user_name;//收款用户姓名	re_user_name	否	王小王	String(64)	收款用户真实姓名。如果check_name设置为FORCE_CHECK，则必填用户真实姓名 如需电子回单，需要传入收款用户姓名

    private Integer amount;//金额	amount	是	10099	int	企业付款金额，单位为分

    private String desc;//企业付款备注	desc	是	理赔	String(100)	企业付款备注，必填。注意：备注中的敏感词会被转成字符*

    //private String spbill_create_ip;//Ip地址	spbill_create_ip	否	192.168.0.1	String(32)	该IP同在商户平台设置的IP白名单中的IP没有关联，该IP可传用户端或者服务端的IP。

}
