package com.third.domain;

import lombok.Data;
import java.util.Map;

@Data
public class SubscribeBO<T> {
    private String access_token;//是	接口调用凭证
    private String touser;	//是	接收者（用户）的 openid
    private String template_id;//是	所需下发的订阅模板id
    private String page;//否	点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。
    private Map<String,SubscribeTemplate> data;//是	模板内容，格式形如 { "key1": { "value": any }, "key2": { "value": any } }
    private String miniprogram_state = "trial";	//否	跳转小程序类型：developer为开发版；trial为体验版；formal为正式版；默认为正式版
    private String lang = "zh_CN";//否	进入小程序查看”的语言类型，支持zh_CN(简体中文)、en_US(英文)、zh_HK(繁体中文)、zh_TW(繁体中文)，默认为zh_C
}
