package com.example.demo;


import com.example.demo.domain.Test;
import com.example.demo.service.OpenFeginDemo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients //启用OpenFeign
@Slf4j
@RefreshScope
@Data
public class SpringcloudServer1Application {

//	@Autowired
//	private ConsulConfig consulConfig;

	@Autowired
	private OpenFeginDemo openFeginDemo;

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudServer1Application.class, args);
//		ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("test.xml");
//		Test test = (Test) xmlApplicationContext.getBean("test");
//		System.out.println(test.getId());
	}

	@GetMapping("/testserver1")
	public String fun(){
//		System.out.println("server1调用");
//		System.out.println(consulConfig.getName());
		return  openFeginDemo.test();
	}
}
