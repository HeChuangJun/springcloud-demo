package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("server2")
public interface OpenFeginDemo {
    @GetMapping("/testserver2")
    public String test();
}
