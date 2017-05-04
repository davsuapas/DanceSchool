package com.elipcero.customerschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CustomerSchoolApplication {

	public static void main(String[] args) {
		 SpringApplication.run(CustomerSchoolApplication.class, args);
	}
}
