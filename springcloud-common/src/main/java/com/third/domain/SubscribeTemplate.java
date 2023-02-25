package com.third.domain;

import lombok.Data;

@Data
public class SubscribeTemplate<T> {
    private String value;

    public SubscribeTemplate(String value) {
        this.value = value;
    }

    private String getValue(){
        return this.value;
    }
}
