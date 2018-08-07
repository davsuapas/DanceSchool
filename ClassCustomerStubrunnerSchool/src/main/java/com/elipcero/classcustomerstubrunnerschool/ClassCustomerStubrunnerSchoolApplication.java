package com.elipcero.classcustomerstubrunnerschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.contract.stubrunner.server.EnableStubRunnerServer;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;

@SpringBootApplication
@EnableStubRunnerServer
@AutoConfigureStubRunner
public class ClassCustomerStubrunnerSchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClassCustomerStubrunnerSchoolApplication.class, args);
	}
}
