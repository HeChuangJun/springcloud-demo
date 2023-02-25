package com.third.domain;

import lombok.Data;

@Data
public class UnifiedOrderVO {
    private String sign_type;
    private String nonce_str;
    private String prepay_id;
    private String timestamp;
    private String sign;
    private String orderId;


}
