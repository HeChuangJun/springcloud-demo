package com.third.domain;

import lombok.Data;

@Data
public class UrllinkBO {
    private String path;
    private String query;
    private String env_version="trial";
    private boolean is_expire;
    private Integer expire_type;
    private Integer expire_time;
    private Integer expire_interval;
    private CloudBase cloud_base = new CloudBase();

}
@Data
class CloudBase{
    private String env = "123";
    private String domain;
    private String path;
    private String query;
    private String resource_appid;
}
