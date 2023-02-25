package com.example.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @RequestMapping("/testauth")
//    @PreAuthorize("hasRole('4')")
//    @PreAuthorize("hasAuthority('4')")//不通过
    @PreAuthorize("hasAuthority('1')")//通过
    public String admin() {
        return "role: ROLE_ADMIN";
    }


}
