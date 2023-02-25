package com.ajun.common;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import java.security.Security;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WeChatPayUtil {

    public static String toXml(Object object){
        if (object == null) return null;
        JSONObject jsonObject = JSONUtil.parseObj(object);

        StringBuilder builder = new StringBuilder();
        Set<String> keySet = jsonObject.keySet();
        Iterator<String> iterator = keySet.iterator();
        builder.append("<xml>");
        while (iterator.hasNext()) {
            String items = iterator.next();
            builder.append("<");
            builder.append(items);
            builder.append(">");
            String value = jsonObject.getStr(items);
            if(Pattern.matches("[0-9]*(\\.?)[0-9]*", value) || Pattern.matches("[0-9]+", value)) {
                builder.append(value);
            }else{
                builder.append("<![CDATA[");
                builder.append(value);
                builder.append("]]>");
            }
            builder.append("</");
            builder.append(items);
            builder.append(">");
        }
        builder.append("</xml>");
        return builder.toString();
    }

    public static String sign(Object object,String key,String... ignoreFields){
        String preSign = keyValuePair(object, ignoreFields) + "&key=" + key;
        return SecureUtil.md5(preSign).toUpperCase();
    }

    /**
     * 把一个Bean对象转换成为key1=value1&key2=value2格式
     */
    public static String keyValuePair(Object object, String... ignoreFields) {
        if (object == null) return null;

        JSONObject jsonObject = JSONUtil.parseObj(object);

        StringBuilder builder = new StringBuilder();
        Set<String> keySet = jsonObject.keySet();
        if (ignoreFields != null && ignoreFields.length > 0) {
            final List<String> ignoreFieldList = Arrays.asList(ignoreFields);
            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                if (ignoreFieldList.contains(iterator.next())) iterator.remove();
            }
        }
        TreeSet<String> sortedSet = CollectionUtil.toTreeSet(keySet, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        for(String key : sortedSet){
            builder.append(key).append("=").append(jsonObject.get(key)).append("&");
        }

        if (builder.length() > 0) {
            builder.setLength(builder.length() - 1);
        }
        return builder.toString();

    }
}
