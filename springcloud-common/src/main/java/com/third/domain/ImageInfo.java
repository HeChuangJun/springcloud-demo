package com.third.domain;

import lombok.Data;

@Data
public class ImageInfo {
    private Integer width;
    private Integer height;
    private String format;
    private String colorModel;
    private String orientation;
}
