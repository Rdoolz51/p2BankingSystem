package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
/*
 * Pretty sure this is on by default with Spring Boot projects that use data,
 * but I'm keeping it.
 */
@EnableTransactionManagement
public class P2BankingSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(P2BankingSystemApplication.class, args);
	}
}
