package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableDiscoveryClient
@SpringBootApplication
@RestController
@Slf4j
public class SpringcloudServer2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudServer2Application.class, args);
	}

	@GetMapping("/testserver2")
	public String fun(){
		System.out.println("server2调用");
		return  "server2调用";
	}
}
