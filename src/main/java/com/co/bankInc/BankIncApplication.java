package com.co.bankInc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.co.bankInc")
public class BankIncApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankIncApplication.class, args);
	}

}