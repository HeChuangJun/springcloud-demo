package com.third.domain;

import lombok.Data;

import java.util.Map;

@Data
public class PublicSubscribeBO {

    private String touser;//	是	接收者openid

    private String template_id;//	是	模板ID

    private String url;//	否	模板跳转链接（海外帐号没有跳转能力）

    private PublicSubscribeMinprogram miniprogram;//	否	跳小程序所需数据，不需跳小程序可不用传该数据

    private Map<String,SubscribeTemplate> data;//是	模板内容，格式形如 { "key1": { "value": any }, "key2": { "value": any } }

    private String color;//	否	模板内容字体颜色，不填默认为黑色

    private String client_msg_id;//	否	防重入id。对于同一个openid + client_msg_id, 只发送一条消息,10分钟有效,超过10分钟不保证效果。若无防重入需求，可不填
}
