package com.ajun.common;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class TestCalculateUtil {
    @Test
    public void testCalculate(){
        Map<String,Object> map = new HashMap<>();
        map.put("a","10");
        map.put("b","20");
        map.put("c","30");
        System.out.println(CalculateUtil.calculate("23*(32-22)+19.6 + 1 - a*b",map));
    }
}
