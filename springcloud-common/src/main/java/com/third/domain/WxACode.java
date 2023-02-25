package com.third.domain;

import lombok.Data;

@Data
public class WxACode {
    //private String access_token;//string		是	接口调用凭证
    private String scene;//	string		是	最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~，其它字符请自行编码为合法字符（因不支持%，中文无法使用 urlencode 处理，请使用其他编码方式）
    private String page;//string	主页	否	必须是已经发布的小程序存在的页面（否则报错），例如 pages/index/index, 根路径前不要填加 /,不能携带参数（参数请放在scene字段里），如果不填写这个字段，默认跳主页面
    //private Integer width;//	number	430	否	二维码的宽度，单位 px，最小 280px，最大 1280px
    //private boolean auto_color;//	boolean	false	否	自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调，默认 false
    //private String  line_color;//	Object	{"r":0,"g":0,"b":0}	否	auto_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"} 十进制表示
    //private boolean is_hyaline;//	boolean	false	否	是否需要透明底色，为 true 时，生成透明底色的小程序
}
