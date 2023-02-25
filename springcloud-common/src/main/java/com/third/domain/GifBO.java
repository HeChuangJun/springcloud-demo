package com.third.domain;

import com.qiniu.util.Auth;
import lombok.Data;

@Data
public class GifBO {
    private String url;
    private String buketName;
    private String orignFileName;
    private String saveAsName;
    private String persistentPipeline;
    private Auth auth;
}