package com.nagarro.accountmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class AccountManagmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountManagmentServiceApplication.class, args);
	}

}
