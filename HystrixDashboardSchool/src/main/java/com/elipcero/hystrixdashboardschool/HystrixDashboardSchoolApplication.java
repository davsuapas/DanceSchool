package com.elipcero.hystrixdashboardschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class HystrixDashboardSchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixDashboardSchoolApplication.class, args);
	}
}
